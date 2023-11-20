package practice;

import java.util.Scanner;

public class PageReplacement
{
    int nof,nop,fault=0,hit=0,pages[],frame[];
    Scanner sc = new Scanner(System.in);
    PageReplacement()
    {
        System.out.println("Enter number of pages: ");
        nop = sc.nextInt();
        System.out.println("Enter number of frames: ");
        nof = sc.nextInt();
        // nop = 19;nof=3;
        pages = new int[nop];
        frame = new int[nof];

        System.out.println("Enter pages: ");
        for(int i=0;i<nop;i++)
        {
            pages[i] = sc.nextInt();
        }
        // pages[0]=3;
        // pages[1]=2;
        // pages[2]=1;
        // pages[3]=3;

        // pages[4]=4;
        // pages[5]=1;
        // pages[6]=6;
        // pages[7]=2;

        // pages[8]=4;
        // pages[9]=3;
        // pages[10]=4;
        // pages[11]=2;

        // pages[12]=1;
        // pages[13]=4;
        // pages[14]=5;
        // pages[15]=2;

        // pages[16]=1;
        // pages[17]=3;
        // pages[18]=4;
    }
    private boolean is_avail(int tp)
    {
        boolean flag=false;
        for(int i=0;i<nof;i++)
        {
            if(frame[i] == tp)
            {
                flag =true;
                break;
            }
        }
        return flag;
    }

    void Display()
    {
        System.out.println("\nPage faults: "+fault+"\nPage Hits: "+hit+"\n");
        fault=0;hit=0;
    }
    void DisplayFrame()
    {
        for(int m=0;m<nof;m++)
        {
               System.out.println(frame[m]);
        }
        System.out.println("\n\n");
    }
    void runFIFO()
    {
        int k=nof;
        for(int i=0;i<nof;i++)
        {
            frame[i] = pages[i];
            fault++;
        }
        for(int i=nof;i<nop;i++)
        {
            if(!is_avail(pages[i]))
            {
                if(k==nof)
                {
                    k=0;
                }
                frame[k] = pages[i];
                fault++;k++;
            }
            else
            {
                hit++;
            }
            
        }
        Display();
    }
    
    int getLRUID(int i)
    {
        int val=0,le=9999;
        for(int j=0;j<nof;j++)
        {
            for(int l=i-1;l>=0;l--)
            {
                if(frame[j]==pages[l] )
                {
                    if(le>l)
                    {
                        le = l;
                        val = j;
                    }
                    
                    break;
                }
            }
        }
        return val;
    }
    void runLRU()
    {
        for(int i=0;i<nof;i++)
        {
            frame[i] = pages[i];
            fault++;
        }

        for(int i=nof;i<nop;i++)
        {
            if(!is_avail(pages[i]))
            {
                frame[getLRUID(i)] = pages[i];
                fault++;
            }
            else
            {
                hit++;
            }
                
        }
        Display();
    }

    int getOPTIMALID(int i)
    {
        int val=0,ge=0;
        for(int j=0;j<nof;j++)
        {
            for(int l=i;l<nop;l++)
            {
                if(frame[j]==pages[l] )
                {
                    if(ge<l)
                    {
                        ge = l;
                        val = j;
                    }
                    DisplayFrame();
                    break;
                }
                if(l==nop-1)
                {
                    if(frame[j]!=pages[l])
                    {
                        val = nof-1;
                    }
                }
                
            }
        }
        return val;
    }
    void runOptimal()
    {
        for(int i=0;i<nof;i++)
        {
            frame[i] = pages[i];
            fault++;
        }

        for(int i=nof;i<nop;i++)
        {
            if(!is_avail(pages[i]))
            {
                frame[getOPTIMALID(i)] = pages[i];
                
                fault++;
            }
            else
            {
                hit++;
            }
                
        }
        Display();
    }



    public static void main(String[] args)
    {
        PageReplacement f = new PageReplacement();
        f.runOptimal();
    }
}