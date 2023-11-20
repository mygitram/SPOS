package practice;

import java.util.Scanner;

import javax.management.Query;

public class CPU_Sheduling {
    int AT[],BT[],CT[],TAT[],WT[],RT[],n,RemTime[],TimeSlice;
    Scanner sc = new Scanner(System.in);

    CPU_Sheduling()
    {
        System.out.println("Enter number of process: ");
        n = sc.nextInt();
        AT = new int[n];
        BT = new int[n];
        CT = new int[n];
        TAT = new int[n];
        WT = new int[n];
        RT = new int[n];
        RemTime = new int[n];
        
		
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter AT for process "+i);
            AT[i] = sc.nextInt();

            System.out.println("Enter BT for process "+i);
            BT[i] = sc.nextInt();
            
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
                }
            }
        }
    }
    void sheduleFCFS()
    {
        int counter=AT[0];
        sort();
        for(int i=0;i<n;i++)
        {
            RT[i] = counter-AT[i];
            counter=counter+BT[i];
            CT[i] = counter;
            if(i<n-2)
            {
                if(AT[i+1]>CT[i])
                {
                    counter = AT[i+1];
                }
            }
           
            TAT[i] = CT[i] - AT[i];

            WT[i] = TAT[i] - BT[i];

        }
    }

   
    boolean allVisited()
    {
        for(int i =0 ;i<n;i++)
        {
            if(RemTime[i]>0)
            {
                return false;
            }
        }
        return true;
    }
    void sheduleSJF()
    {
        sort();
        int i=0,t=1,qe=0;
        while(!allVisited())
        {
            t=1;
            if(RemTime[i]<=0)
            {
                t=0;
            } 
            RemTime[i] = RemTime[i] - t;
            qe = qe+t;
            CT[i] = qe;

            int sm=999,small=0;
            for(int j=0;j<n && j<=qe;j++)
            {
                if(sm>RemTime[j]&&RemTime[j]!=0)
                {
                    sm=RemTime[j];
                    small=j;
                }

            }
            i=small;

        }

        int AVGWT=0,AVTAT=0;
        for(int k=0;k<n;k++)
        {
            TAT[k] = CT[k] - AT[k];
            WT[k] = TAT[k] - BT[k];
            AVGWT = AVGWT + WT[k];
            AVTAT = AVTAT + TAT[k];
        }
    }

    void sheduleRR()
    {
        sort();
		int counter = 0,qe=0,t=0;
		int temp=0;
        queue ReadyQue = new queue();
        queue RunningQueue = new queue();
        int i=0;

        for(int j=0;j<n;j++)
        {
            if(AT[j] <=qe)
            {
                ReadyQue.push(j);
            }
        }
		while(!ReadyQue.empty())
		{
            // RT is equal to remaining time 
            temp = ReadyQue.pop();
            RunningQueue.push(temp);

            if(RT[temp]>=TimeSlice)
            {
                t = TimeSlice;
            }
            else
            {
                t = RT[temp];
            }
            RT[temp] = RT[temp] - t;
            qe = qe + t;
            CT[temp] = qe;
            
            for(int j=(qe-t)+1;j<n;j++)
            {
                if(AT[j] <=qe)
                {
                    ReadyQue.push(j);
                }  
            }
            if(RT[temp]>0)
            {
                ReadyQue.push(temp);
            }
		}			

/*
        TimeSlice=2;
		AT[0] = 0;BT[0] = 5;
		AT[1] = 1;BT[1] = 4;
		AT[2] = 2;BT[2] = 2;
		AT[3] = 4;BT[3] = 1;
 */
    }

    void Display()
    {
        System.out.println("Process \t AT \t BT \t CT \t TAT \t WT \t RT\n");
        for(int i=0;i<n;i++)
        {
            System.out.println(i+"\t\t"+AT[i]+"\t"+BT[i]+"\t"+CT[i]+"\t"+TAT[i]+"\t"+WT[i]+"\t"+RT[i]);
        }
    }
    public static void main(String[] args){
        CPU_Sheduling c = new CPU_Sheduling();
        c.sheduleRR();
        c.Display();
    }
}



















class queue{
    int q[],head,tail,n;
    queue()
    {
        head=tail=-1;
        n=10;
        q = new int[n];
    }
    void push(int i)
    {
        if(!full())
        {
            if(head==-1)
            {
                head=0;
            }
            tail++;
            q[tail] = i;
        }
    }
    int pop()
    {
        int temp=0;
        if(!empty())
        {
            if(head==tail)
            {
                temp = q[head];
                head=tail=-1;
            }
            else
            {
                temp = q[head];
                head++;
            }
            
        }
        else
        {
            System.out.println("Cant pop");
        }
        return temp;
    }
    boolean empty()
    {
        if(head==-1)
        {
            return true;
        }
        return false;
    }
    boolean full()
    {
        if(tail==n-1)
        {
            return true;
        }
        return false;
    }
}