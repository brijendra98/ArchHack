import java.io.*;
import java.util.*;

public class hospital
{
String name;
double T;
int PT;
double TT;
double WT;
double AT;
double R;
String[] specialization;
double Val;
String Email;

public hospital(int pt,double tt,double at,double r, String[] spec, String nm,String email)
{
    name=nm;
    PT=pt;
    TT=tt;
    AT=at;
    R=r;
    Email=email;
    WT=pt*at;
    T=tt+WT;
    specialization=new String[spec.length];
    System.arraycopy(spec, 0, specialization, 0, spec.length);
    Val= (20*R) + ((80*60)/T);
}
    
public void main(int pt,double tt,double at,double r, String[] spec,String nm,String email)
{
    hospital obj1= new hospital(pt,tt,at,r,spec,nm,email);
    //Scanner -> file(/)
    //split (',')
    //go by line 
    //buffer reader
    //line = []
    //obj1 = new hospline[]0
}
}

class calculate
{

    hospital[] arrobj =new hospital[9];
    hospital BestHospital;
    
    /**
     *
     * @param x
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public hospital read(int x) throws FileNotFoundException, IOException
{
    
    FileInputStream fs= new FileInputStream("C://Users/HP/Desktop/data.csv");
    BufferedReader br = new BufferedReader(new InputStreamReader(fs));
    for(int i = 0; i < x; ++i)
        br.readLine();
String str = br.readLine();
String[] ar= str.split(",");
String nm=ar[5];
String email=ar[6];
    int pt=Integer.parseInt(ar[0]);
    double tt=Double.parseDouble(ar[1]);
    double at=Double.parseDouble(ar[2]);
    double r=Double.parseDouble(ar[3]);
    String[] spec=convert(ar[5]);
    hospital H1=new hospital(pt,tt,at,r,spec,nm,email);
    return H1;
    
}

public String[] convert(String str)
{
    int c=1;
    
    for(int i=1;i<str.length();i++)
    {
        char ch=str.charAt(i);
        if(ch==',')
            c++;
    }
    
    String[] arr= new String[c];
    int x=0;
    int a=1;
    for(int i=1;i<str.length();i++)
    {
        char ch=str.charAt(i);
        
        if(ch==','||ch=='}')
        {
            int b=i;
            arr[x]=str.substring(a,b);
            x++;
            a=b+1;
        }
        }
    return arr;
    }

public void CreateArray() throws IOException
{
     calculate obj=new calculate();
    for(int i=1;i<=9;i++)
    {
      hospital obj2=obj.read(i);
      arrobj[i-1]=obj2;
    }

}

public void SortByVal(hospital[] arr)
{
     int j;
     boolean flag = true;   // set flag to true to begin first pass
     hospital temp;   //holding variable

     while ( flag )
     {
            flag= false;    //set flag to false awaiting a possible swap
            for( j=0;  j < arr.length -1;  j++ )
            {
                   if ( arr[ j ].Val < arr[j+1].Val )   // change to > for ascending sort
                   {
                           temp = arr[ j ];                //swap elements
                           arr[ j ] = arr[ j+1 ];
                           arr[ j+1 ] = temp;
                          flag = true;              //shows a swap occurred  
                  } 
            } 
      } 
}

  
public void main(String args[]) throws IOException
{
    calculate obj1=new calculate();
    obj1.CreateArray();
    obj1.SortByVal(arrobj); 
    BestHospital= arrobj[0];
   
    
    System.out.println("Hello World");
}


}