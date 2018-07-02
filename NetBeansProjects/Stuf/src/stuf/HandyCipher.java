package stuf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author User
 */
public class HandyCipher {
    private char [][] t=new char[10][5];
    private int x=0;
    private  int[] slopearray={2,0,1,-1};
    private String s1="QjufGCtwbUSNLqHAgVDOoansIhyBKJWFdxvPkpeXMTlirYRmcE",s2="QjufGCtwbUSNLqHAgVDOoansIhyBKJWFdxvPk^peXMTlirYRmcE",coded;
    private HashMap<Character,Integer> st=new HashMap();
    HandyCipher(){
              // s2=rand(s2);
              s1=s2.replace("^","");
              st=GenSubstTable(s2);
        for(int i=0;i<5;i++){
            for(int z=0;z<10;z++){
                t[z][i]=s1.charAt((i+1)*(z));
               // System.out.print(s1.charAt(z+x)+" "); 
        }
            x=x+10;
            //System.out.print("\n");
        }
        x=0;
            for(int i=0;i<5;i++){
            for(int z=0;z<10;z++){
                t[z][i]=s1.charAt(z+x);
                System.out.print(t[z][i]+" "); 
        }
            x=x+10;
            System.out.print("\n");
        }
    }
    private HashMap GenSubstTable(String s){
        HashMap <Character,Integer> t=new HashMap();
        int n=1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='a'){
                t.put('Z', n);n++;}
             else if(s.charAt(i)=='b'){
                t.put(',', n);n++;}
                else if(s.charAt(i)=='c'){
                t.put('.',n);n++;}
                   else if(s.charAt(i)=='d'){
                t.put('?',n);n++;}
                      else if(s.charAt(i)=='e'){
                t.put('-',n);n++;}
                      else if(Character.isLowerCase(s.charAt(i))){}
            else {
        t.put(s.charAt(i), n);n++;}
        }
    return t;
    }
        
    public String encode(String h){
h=h.toUpperCase();
h=h.replace(" ","^");
coded="";
boolean singel=false;
Random rand=new Random();
int p,c,mar=0,l;
String string="",string2="";
for(int i=0;i<h.length();i++){
p=st.get(h.charAt(i));
string="";
if(p==1||p==2||p==4||p==8||p==16)
l=0;
else 
l=rand.nextInt(4);
l=0;
switch(l){
    case 0:{
c=rand.nextInt(5);
     if(i%2==0)
           for(int j=0;j<5;j++){
            if(getBit(p,j)){
        string=string+t[c][j];
        }
        }
     else
               for(int j=4;j>=0;j--){
            if(getBit(p,4-j)){
        string=string+t[c][j];
        }
        }
        break;
    }
    case 1:{
     c=rand.nextInt(5);
     if(h.length()>i+1)
      if(i%2==0&&st.get(h.charAt(i+1))==Math.pow(2,4-c)
              ||i%2==1&&st.get(h.charAt(i+1))==Math.pow(2,c)){
          //System.out.print("Continuie");
          i--;}
     if(i%2==0)
           for(int j=0;j<5;j++){
            if(getBit(p,j)){
        string=string+t[j][c];
        }
        }
     else
               for(int j=4;j>=0;j--){
            if(getBit(p,4-j)){
        string=string+t[j][c];
        }
        }
        break;}
    case 2:{
     c=rand.nextInt(5);
     if(i%2==0)
           for(int j=0;j<5;j++){
            if(getBit(p,j)){
        string=string+t[c%5][j];
        
        }
            c++;
        }
     else
               for(int j=4;j>=0;j--){
            if(getBit(p,4-j)){
        string=string+t[Math.abs(c%5)][j];
      
        }
              c--;
        }
        break;}
    case 3:{
        c=rand.nextInt(5);
        if(i%2==0)
           for(int j=0;j<5;j++){
            if(getBit(p,j)){
        string=string+t[Math.abs(c%5)][j];
      
        }
              c--;
        }
     else
               for(int j=4;j>=0;j--){
            if(getBit(p,4-j)){
        string=string+t[c%5][j];
        
        }
            c++;
        } 
        break;
       }  
}
int []cor1=new int[2],cor2=new int[2];
string=rand(string);
string2=rand(string2);
//System.out.print("&"+i+"&");
if(h.length()==1)
coded=coded+string;
else if(string2.length()>0){
    
    cor1=find(string2.charAt(string2.length()-1));
if(lineSlope(cor1[0],cor1[1],string.charAt(0))!=slopearray[l]){

if(string2.length()==1&&lineSlope(cor1[0],cor1[1],string.charAt(0))==-2){
    //coded=coded+InsertNoise(string2);
    coded=coded+(string2);
    //singel=true;
   // System.out.print(" "+coded+" ");
    System.out.print("Case 1"+p+" ");
    string2=string;
}
else if(lineSlope(cor1[0],cor1[1],string.charAt(0))!=-2){
    System.out.print("Case 2"+p+" ");
    singel=false;
//coded=coded+InsertNoise(string2);
coded=coded+(string2);
//System.out.print(" "+coded+" ");
string2=string;
}
else{
    System.out.print("Case 3");
i--;
    singel=false;
string="";
}
}
else{
   System.out.print("Case 4");
i--;
    singel=false;
string="";
}
}
else
string2=string;
}

//System.out.print(" "+coded+string+" ");

//return coded+InsertNoise(string);
return coded+(string);
    }
    private String InsertNoise(String s){
        Random rand=new Random();
        if(s.length()<3)
            return s;
        int np=rand.nextInt(5)+1,l=s.length();
        int [] cor1;
        cor1=find(s.charAt(0));
        
        //System.out.print(" "+s+" ");
         //System.out.print(np+"");
    for(int i=2;i<l;i++){
        if(np>rand.nextInt(6)){
          char c=genNoiseCharacter(cor1[0],cor1[1]);
         System.out.print("<"+c+">");
    s=s.substring(0,i)+c+s.substring(i,s.length());i++;}
    }
    return s;
    }
    private int lineSlope(int x1,int y1,int x2,int y2){
        int r;
        if(x1==x2)
            r=2;
        else if(y1==y2)
            r=0;
        else 
            r=(y2-y1)/(x2-x1);
    return r;
    }
    private char genNoiseCharacter(int x1,int y1){
        Random rand=new Random();
        int [] dir1={2,-2},dir2={1,-1};
        char n='c';
if(rand.nextInt(2)==1){
n=t[(Math.abs(x1+dir1[rand.nextInt(2)]+5))%5][(Math.abs(y1+dir2[rand.nextInt(2)]+5))%5];
}
else{
n=t[(Math.abs(x1+dir2[rand.nextInt(2)]+5))%5][(Math.abs(y1+dir1[rand.nextInt(2)]+5))%5];
}
    return n;
    }
public boolean getBit(int number,int position){
   return ((number >> position) & 1)==0 ? false:true;
}
    private String rand(String s){
        char c;
        int r;
        Random rand=new Random();
    for(int i=0;i<s.length();i++){
        r=rand.nextInt(s.length()-i)+i;
       c=s.charAt(r);
      s=s.substring(0,r)+s.substring(r+1);
      s=c+s;
    }
    return s;
    }
    public String decode(String coded){
        String messege="";
        boolean y=false,v=false;
        int []c=new int[2];     
        int slope=0,w,z,n=0,mar=0;
        char ch;
    for(int i=0;i<coded.length()-1;){
    c=find(coded.charAt(i));
    //System.out.print(c[0]+" "+c[1]);
    
    slope=lineSlope(c[0],c[1],coded.charAt(i+1));
    //if(coded.charAt(i))
 v=false;y=false;
    switch(2){
        case 0:{
            System.out.print("Case 0:");
while(i<coded.length()){
if(i+1<coded.length()){
if(lineSlope(c[0],c[1],coded.charAt(i+1))!=0){
   if(i+2<coded.length()){
   if(lineSlope(c[0],c[1],coded.charAt(i+2))!=0)
       y=true;
   else 
       v=true;
   }
}

}
if(mar%2==0){
    w=0;
    z=c[1];
for(int j=0;j<5&&i<coded.length();j++){
if(t[w][z]==coded.charAt(i)){
n=n|1<<j;}
w++;
}
}
else{
    w=4;
    z=c[1];
    for(int j=4;j>=0&&i<coded.length();j--){
if(t[w][z]==coded.charAt(i)){
n=n|1<<4-j;}
w--;
}
}
i++;
if(y){
    mar++;
break;
}
else if(v){}

}
            break;
        }
        case 2:{
            //System.out.print("Case 2:");
            int g=0;
        while(i<coded.length()){
if(i+1<coded.length()){
    c=find(coded.charAt(i));
if(lineSlope(c[0],c[1],coded.charAt(i+1))!=2){
   
   if(lineSlope(c[0],c[1],coded.charAt(i+1))!=-2&&(g!=1||g!=2||g!=4||g!=8||g!=16))
       y=true;
   else 
       v=true;
   
}
}
if(mar%2==0){
    w=c[0];
    z=0;
for(int j=0;j<5&&i<coded.length();j++){
if(t[w][z]==coded.charAt(i)){
n=n|1<<j;}
z++;
}
}
else{
    w=c[0];
    z=4;
    for(int j=4;j>=0&&i<coded.length();j--){
if(t[w][z]==coded.charAt(i)){
n=n|1<<4-j;}
z--;
}
}
i++;
if(y){
    mar++;
break;
}
else if(v){i++;}
g=n;
}
            break;}
        case 1:{System.out.print("Case 1:");}
        case -1:{System.out.print("Case -1:");}
        default:{System.out.print("default");i++;break;}
    }
    for (HashMap.Entry<Character,Integer> entry : st.entrySet()){
    if(entry.getValue()==n){
        messege=messege+entry.getKey();
        n=0;
    }
}
    }
   // System.out.print(messege+" ");
    return messege;}
       private int lineSlope(int x,int y,char c){
           if(t[x][y]==c)
               return -2;
             for(int i=0;i<5;i++){
            if(t[(i)%5][y]==c)
                return 0;
        }
    for(int i=0;i<5;i++){
            if(t[x][(i)%5]==c)
                return 2;
        }
     for(int i=0;i<5;i++){
            if(t[(5-Math.abs(x-y)+i)%5][(i)%5]==c)
                return 1;
        } 
       for(int i=0;i<5;i++){
            if(t[Math.abs(x+y-i)%5][((i)%5)]==c)
                return -1;
        } 
    return -2;
    }
            private int[] lineSearch(int x,int y,char c,int slope){
        int [] cor=new int[2];
        switch(slope){
            case 0:{}
            case 1:{}
            case -1:{}
            case 2:{}
        }
    return cor;
    }
    private int[]  find(char l){
        int [] c=new int[2];
        for(int i=0;i<t.length;i++){
        for(int j=0;j<t[0].length;j++){
        if(t[i][j]==l){
            c[0]=i;
        c[1]=j;}
        }
        }
    return c;
    }
}
