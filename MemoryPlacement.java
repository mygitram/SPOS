package practice;
import java.util.*;
class Memory
{
    int MB[],PR[],Allocation[],noblock,noprocess;

    Scanner sc = new Scanner(System.in);
    Memory()
    {
        System.out.println("Enter number of Memory blocks: ");
        noblock = sc.nextInt();
        System.out.println("Enter number of Process: ");
        noprocess = sc.nextInt();

        // noblock = 5;noprocess=4;
        MB = new int[noblock];
        Allocation = new int[noblock];
        PR = new int[noprocess];
    }
    void readData()
    {
        for(int i=0;i<noblock;i++)
        {
            System.out.println("Enter memory blocks: ");
            MB[i] = sc.nextInt();
            Allocation[i] = -1;
        }
        for(int i=0;i<noprocess;i++)
        {
            System.out.println("Enter process: ");
            PR[i] = sc.nextInt();
        }
        // MB[0] = 100;
        // MB[1] = 500;
        // MB[2] = 200;
        // MB[3] = 300;
        // MB[4] = 600;

        // PR[0] = 212;
        // PR[1] = 417;
        // PR[2] = 112;
        // PR[3] = 426;
    }
    void Display()
    {
        System.out.println("\n\nMemoryBlock \t Process");
        for(int i=0;i<noblock;i++)
        {
            System.out.println(""+MB[i]+" \t\t "+Allocation[i]);
        }
    }
    void FirstFit()
    {
        int flag=0;
        for(int i=0;i<noprocess;i++)
        {
            flag=0;
            for(int j=0;j<noblock;j++)
            {
                if(PR[i]<=MB[j] && Allocation[j]==-1)
                {
                    Allocation[j] = PR[i];
                    flag=1;
                    break;
                }

            }
            if (flag==0) {
                System.out.println("Process "+i+" with size "+PR[i]+" goes into starvation");
            }
        }
    }
    void NextFit()
    {
        int flag=0,ctr=0;
        for(int i=0;i<noprocess;i++)
        {
            flag=0;
            for(int j=ctr;j<noblock;j++)
            {
                if(PR[i]<=MB[j] && Allocation[j]==-1)
                {
                    Allocation[j] = PR[i];
                    flag=1;
                    ctr = j;
                    if(ctr==noblock-1)
                    {
                        ctr=0;
                    }
                    break;
                }

            }
            if (flag==0) {
                System.out.println("Process "+i+" with size "+PR[i]+" goes into starvation");
            }
        }
    }
    void BestFit()
    {
        int less,l;
        for(int i=0;i<noprocess;i++)
        {
            less=9999;l=-1;
            for(int j=0;j<noblock;j++)
            {
                if(PR[i]<=MB[j] && Allocation[j]==-1)
                {
                    if(less>MB[j])
                    {
                        less = MB[j];
                        l=j;
                    }
                    
                }
            }
            if(l==-1)
            {
                System.out.println("Process "+i+" with size "+PR[i]+" goes into starvation");
            }
            else
            {
                Allocation[l] = PR[i];
            }
        }
    }
    void WorstFit()
    {
        int big,l=-1;
        for(int i=0;i<noprocess;i++)
        {
            big=0;l=-1;
            for(int j=0;j<noblock;j++)
            {
                if(PR[i]<=MB[j] && Allocation[j]==-1)
                {
                    if(big<MB[j])
                    {
                        big = MB[j];
                        l=j;
                    }
                    
                }
            }
            if(l==-1)
            {
                System.out.println("Process "+i+" with size "+PR[i]+" goes into starvation");
            }
            else
            {
                Allocation[l] = PR[i];
            }
        }
    }
}
public class MemoryPlacement{
    public static void main(String[] args)
    {
        Memory m =new Memory();
        m.readData();
        m.FirstFit();
        m.Display();
    }
}