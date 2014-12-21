#define MASK_ALL8F              0xFFFFFFFF
#define MASK_HI16               0xFFFF0000
#define MASK_LO16               0x0000FFFF
#define MASK_LO08               0x000000FF
#define MASK_LO04               0x0000000F

#define TRUE            0xEB90146F
#define FALSE           0x00000000

#define OUTPUT_SIZE            3
#define INPUT_SIZE             8

#define IO3_UART1_CS           ((volatile unsigned int *)0x130000A0)
#define IO3_UART2_CS           ((volatile unsigned int *)0x130000C0)
#define IO3_UART3_CS           ((volatile unsigned int *)0x130000E0)

static volatile unsigned int *tmpAddr = 0;
static struct task_struct *taskThread = 0;
typedef struct TAG_BUFFER_INPUT
{
	unsigned int wrlen;
	unsigned int wrbuf[OUTPUT_SIZE];
	unsigned int rdlen;
	unsigned int rdbuf[INPUT_SIZE];
	unsigned int bflgComm;
	unsigned int Status;
} SInput;

SInput mInput[3];

void ReadASIC(volatile unsigned int *BasedAddr, unsigned int *rdbuf, unsigned int *rdlen, int Size, unsigned int *FlgComm)
{
	int ird;
	volatile unsigned int *RBR;
	volatile unsigned int *LSR;
	volatile unsigned int *FCR;

	RBR = BasedAddr;
	FCR = BasedAddr + 2;
	LSR = BasedAddr + 5;

	for (ird=0; ird<Size; ird++)
	{
		rdbuf[ird] = 0x00;
	}

	(*rdlen) = 0;

	while ((((*LSR) & 0x1) == 0x1) && ((*rdlen) < (unsigned int)Size))
	{
		rdbuf[*rdlen] = (*RBR) & MASK_LO08;

		(*rdlen) = (*rdlen) + 1;
	}

	*FCR = 0xE9;
	*FCR = 0xEF;

	if ((*rdlen) < (unsigned int)Size)
	{
		*FlgComm = FALSE;
	}
	else
	{
		*FlgComm = TRUE;
	}

	return;
}

void WriteASIC(volatile unsigned int *BasedAddr, unsigned int *wrbuf, unsigned int *wrlen)
{
	unsigned int jwr;
	volatile unsigned int *TBR;
	volatile unsigned int *LSR;
	volatile unsigned int *FCR;

	TBR = BasedAddr;
	FCR = BasedAddr + 2;
	LSR = BasedAddr + 5;

	*FCR = 0xE9;
	*FCR = 0xEF;
	Delay(200);		

	if (((*LSR) & 0x20) == 0x20)
	{
		for (jwr=0; jwr<(*wrlen); jwr++)
		{
			*TBR = (*(wrbuf + jwr)) & MASK_LO08;
		}
	}

	(*wrlen) = 0;

	return;
}

void main(void)
{
	mInput[0].rdlen = 0;
	mInput[1].wrlen = 2;
	ReadASIC(tmpAddr, mInput[0].rdbuf, &mInput[0].rdlen, INPUT_SIZE, &mInput[0].bflgComm);
	WriteASIC(tmpAddr, mInput[1].wrbuf, &mInput[1].wrlen);

	return;
}
