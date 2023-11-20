package practice;

import java.util.Scanner;

class prioritySheduling{
    int AT[],BT[],CT[],TAT[],WT[],RT[],priority[],n,RemTime[],TimeSlice,status[];
    Scanner sc = new Scanner(System.in);

    prioritySheduling()
    {
        // System.out.println("Enter number of process: ");
        // n = sc.nextInt();
        n=7;
        AT = new int[n];
        BT = new int[n];
        CT = new int[n];
        TAT = new int[n];
        WT = new int[n];
        RT = new int[n];
        RemTime = new int[n];
        status = new int[n];
		priority = new int[n];
        AT[0] = 0;
        AT[1] = 1;
        AT[2] = 3;
        AT[3] = 4;
        AT[4] = 5;
        AT[5] = 6;
        AT[6] = 10;

        BT[0] = 8;
        BT[1] = 2;
        BT[2] = 4;
        BT[3] = 1;
        BT[4] = 6;
        BT[5] = 5;
        BT[6] = 1;

        priority[0] = 3;
        priority[1] = 4;
        priority[2] = 4;
        priority[3] = 5;
        priority[4] = 2;
        priority[5] = 6;
        priority[6] = 1;
        
        for(int i=0;i<n;i++)
        {
            // System.out.println("Enter AT for process "+i);
            // AT[i] = sc.nextInt();

            // System.out.println("Enter BT for process "+i);
            // BT[i] = sc.nextInt();

            // System.out.println("Enter priority for process "+i);
            // priority[i] = sc.nextInt();
            status[i] =0;
            RemTime[i] = BT[i];
            CT[i] = 0;
            TAT[i] = 0;
            WT[i] = 0;
            RT[i] = BT[i];
            
        }
    }
    void sort()
    {
        int temp;
        for(int i =0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(AT[i]<AT[j])
                {
                    temp = AT[i];
                    AT[i] = AT[j];
                    AT[j] = temp;
                    
                    temp = BT[i];
                    BT[i] = BT[j];
                    BT[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;
                }
            }
        }
    }
    void shedule()
    {
        int temp=0,counter=0,i=0,j=0,less=999,l=0;
        for(i=0;i<n;i++)
        {
            less=999;
            for(j=0;j<n;j++)
            {
                if(less>priority[j] && counter>=AT[j] && status[j]==0)
                {
                    less = priority[j];
                    l= j;
                }
            }
            RT[l] = counter-AT[l];
            counter = counter+BT[l];
            CT[l] = counter;
            status[l] = 1;
        }
    }
    void Display()
    {
        System.out.println("Process \t Priority \t AT \t BT \t CT \t TAT \t WT \t RT\n");
        for(int i=0;i<n;i++)
        {
            TAT[i] = CT[i] - AT[i];
            WT[i] = TAT[i] - BT[i];
            System.out.println(i+"\t\t"+priority[i]+"\t\t"+AT[i]+"\t"+BT[i]+"\t"+CT[i]+"\t"+TAT[i]+"\t"+WT[i]+"\t"+RT[i]);
        }
    }
    public static void main(String[] args)
    {
        prioritySheduling p=new prioritySheduling();
        p.shedule();
        p.Display();
    }
}