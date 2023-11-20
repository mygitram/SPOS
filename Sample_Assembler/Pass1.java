package practice.Sample_Assembler;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class Pass1 {
    public static void main(String[] args)
    {
        FileReader fr = null;
        BufferedReader br=null;
        FileWriter F_SYMTB=null;
        BufferedWriter SYMTB=null;
        FileWriter F_LITTAB=null;
        BufferedWriter LITTAB=null;
        FileWriter F_IC=null;
        BufferedWriter IC=null;
        String str=null,s1,s2=null,s3=null,s4=null,ts1,ts2=null;
        int arrayptr=0;
        String array[];
        int lno=0,cons=0,IS=0,AD=0,DL=0,inc=1,SYMCTR=0,LITCTR=0;
try
{
        array = new String[10];
        fr = new FileReader("D:\\AVCOE\\TE\\Practicals\\SPOS_FINAL\\practice\\Sample_Assembler\\input.txt");
        br = new BufferedReader(fr);
        F_SYMTB = new FileWriter("D:\\AVCOE\\TE\\Practicals\\SPOS_FINAL\\practice\\Sample_Assembler\\SymbolTable.txt");
        SYMTB = new BufferedWriter(F_SYMTB);
        F_LITTAB = new FileWriter("D:\\AVCOE\\TE\\Practicals\\SPOS_FINAL\\practice\\Sample_Assembler\\LiteralTable.txt");
        LITTAB = new BufferedWriter(F_LITTAB);
        F_IC = new FileWriter("D:\\AVCOE\\TE\\Practicals\\SPOS_FINAL\\practice\\Sample_Assembler\\IC.txt");
        IC = new BufferedWriter(F_IC);

        str = br.readLine();
        s1=str.split(" ")[1];
        if(s1.equals("START"))
        {
            s2=str.split(" ")[2];
            lno = Integer.parseInt(s2);
            IC.write("AD"+"\t01"+"\tC\t"+lno+"\n");
            
            System.out.println("AD"+"\t"+lno+"\n");
            cons = lno;
        }

        while ((str = br.readLine()) != null) 
        {
            inc=1;
            s1=str.split(" ")[0];
            s2=str.split(" ")[1];
            if(str.split(" ").length>2)
            {
                
                s3=str.split(" ")[2];
            }

            
            

            
            if(s2.equals("STOP"))
            {
                IC.write("IS\t"+"00");
            }
            if(s2.equals("ADD"))
            {
                IC.write("IS\t"+"01\t");
            }
            if(s2.equals("SUB"))
            {
                IC.write("IS\t"+"02\t");
            }
            if(s2.equals("MULT"))
            {
                IC.write("IS\t"+"03\t");
            }
            if(s2.equals("MOVER"))
            {
                IC.write("IS\t"+"04\t");
            }
            if(s2.equals("MOVEM"))
            {
                IC.write("IS\t"+"05\t");
            }
            if(s2.equals("COMP"))
            {
                IC.write("IS\t"+"06\t");
            }
            if(s2.equals("BC"))
            {
                IC.write("IS\t"+"07\t");
            }
            if(s2.equals("DIV"))
            {
                IC.write("IS\t"+"08\t");
            }
            if(s2.equals("READ"))
            {
                IC.write("IS\t"+"09\t");
            }
            if(s2.equals("PRINT"))
            {
                IC.write("IS\t"+"10\t");
            }
            
            if(s2.equals("START"))
            {
                IC.write("AD\t"+"01");
            }
            if(s2.equals("END"))
            {
                IC.write("AD\t"+"02");
            }
            if(s2.equals("ORIGIN"))
            {
                IC.write("AD\t"+"03");
            }
            if(s2.equals("EQU"))
            {
                IC.write("AD\t"+"04");
            }
            if(s2.equals("LTORG"))
            {
                IC.write("AD\t"+"05");
            }

            if(s2.equals("DC"))
            {
                IC.write("DL\t"+"01\tC\t"+s3+"\n");
                SYMTB.write(s1+"\t"+lno+"\n");
                inc = Integer.parseInt(s3);


            }
            if(s2.equals("DS"))
            {
                IC.write("DL\t"+"02\tC\t"+s3+"\n");
                SYMTB.write(s1+"\t"+lno+"\n");
                inc = Integer.parseInt(s3);
            }
            if(!(s1.equals("")) &&  !(s2.equals("DS")) &&   !(s2.equals("DC")))
            {
                SYMTB.write(s1+"\t"+lno+"\n");
                SYMCTR++;
            }
            
            ts1 = s3.split(",")[0];
            if(ts1.equals("AREG") || ts1.equals("BREG") ||ts1.equals("CREG") || ts1.equals("CREG") )
            {
                ts2 = s3.split(",")[1];
            }
            
            if(ts1.equals("AREG"))
            {
                IC.write("1\t");
                if(ts2.contains("="))
                {
                    LITCTR++;
                    IC.write("L\t"+LITCTR+"\n");
                    // LITTAB.write(ts2+"\t\n");
                    array[arrayptr] = ts2;
                    arrayptr++;
                }
                else
                {
                    SYMCTR++;
                    IC.write("S\t"+SYMCTR+"\n");
                }
                
            }
            if(ts1.equals("BREG"))
            {
                IC.write("2\t");
                if(ts2.contains("="))
                {
                    LITCTR++;
                    IC.write("L\t"+LITCTR+"\n");
                    // LITTAB.write(ts2+"\t\n");
                    array[arrayptr] = ts2;
                    arrayptr++;
                }
                else
                {
                    SYMCTR++;
                    IC.write("S\t"+SYMCTR+"\n");
                }
                
            }
            if(ts1.equals("CREG"))
            {
                IC.write("3\t");
                if(ts2.contains("="))
                {
                    LITCTR++;
                    IC.write("L\t"+LITCTR+"\n");
                    // LITTAB.write(ts2+"\t\n");
                    array[arrayptr] = ts2;
                    arrayptr++;
                }
                else
                {
                    SYMCTR++;
                    IC.write("S\t"+SYMCTR+"\n");
                }
                
            }
            if(ts1.equals("DREG"))
            {
                IC.write("4\t");
                if(ts2.contains("="))
                {
                    LITCTR++;
                    IC.write("L\t"+LITCTR+"\n");
                    // LITTAB.write(ts2+"\t\n");
                    array[arrayptr] = ts2;
                    arrayptr++;
                }
                else
                {
                    SYMCTR++;
                    IC.write("S\t"+SYMCTR+"\n");
                }
                
            }
            

            if(s2.equals("END")||s2.equals("LTORG"))
            {
                for(int i=0;i<arrayptr;i++)
                {
                    LITTAB.write(array[i]+"\t"+lno+"\n");
                    lno++;
                }
                arrayptr=0;
            }
            lno = lno+inc;
        }
           
        

        IC.close();
        SYMTB.close();
        LITTAB.close();
}
catch(Exception e)
{
    System.out.println(e);
}
    



    }
}
