package conv;
  
import java.util.ArrayList;
import java.util.Arrays;
  
import java.util.ArrayList;
public class test {
    public static  void main(String[] args) {
 
        class Number{
        //=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
            // your method for converting belongs here...
        //=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
            //It got compicated because convertfracions uses convert so I created another method that puts everything together
            public Number finalconversion(Number A, short Base){
                Number tmp_1= this.convert(this, Base);
                Number tmp_2= A.convertfraction(A, Base);
                Number output= new Number();
                output.Base=this.Base;
                output.NonRep=tmp_2.NonRep;
                output.Rep=tmp_2.Rep;
                output.Int=this.Int;
                return output;
                 
                         
            }
             
             
            public Number convert(Number A, short Base) {
                Number B=new Number();
                B=A;
                Boolean b= true;
                Number Base_1 = new Number();
                Base_1.Int= new short[1];
                Base_1.Int[0]=Base;
                ArrayList<Short> Remainders =new ArrayList<Short>();
                Number quotient = new Number();
                quotient =A;
                //while loop until quotient smaller than Base_1, then store all remainders in one arraylist
                //Note that division method also stores the remainders
                while(b){
                    quotient=quotient.division(Base_1);
                    //Remainders and last quotient added
                    Remainders.add(quotient.Rep[0]);
                    if(!quotient.subtract(Base_1).checknegative()){
                        b=false;
                        Remainders.add(quotient.Int[0]);
                    }
                }
                B.Int= new short[Remainders.size()];
                for(int i = 0 ; i<Remainders.size(); i++)
                {
                    B.Int[i] = Remainders.get(i);
                }
                 
                     
                return B;
            }
            public Number convertfraction(Number A,short Base){
                Number tmp = new Number();
                tmp.Int=A.NonRep; 
                tmp.Base=A.Base;
                Number P= new Number();
                P.Int=A.NonRep;
                Number tmp2= new Number();
                tmp2.Int= new short[A.NonRep.length+1];
                for (int i =0; i<NonRep.length; i++){
                    tmp2.Int[i] = 0;
                }
                tmp2.Int[NonRep.length]= 1;
                tmp2.Base=A.Base;
                Number Q= new Number();
 
                P= tmp.convert(tmp,Base);
                Q= tmp2.convert(tmp2,Base);
                Number storage = new Number();
                Number Base_1 = new Number();
                Base_1.Int= new short[1];
                Base_1.Int[0]=Base;
                //setting the bases to base 2
                 
                Q.Base=Base;
                //Q is denominator
                P.Base=Base;
                //P is numerator
         
                ArrayList<short[]> Remainders = new ArrayList<short[]>();
                ArrayList<Short> ratio = new ArrayList<Short>();
                //counter describes negative power of base. counter 4 = 10^-4
                Boolean duplicate= true;
 
                //probably didn't need use index but decided to put it in here for shorten writing
                int index = 0; 
                int[] detector_1= new int[1];
                int[] detector_2= new int[1];
                while(duplicate){
                    P=P.multiply(Base_1);
                    storage=P.division(Q);
                    ratio.add(storage.Int[storage.Int.length-1]);
                    P.Int=P.division(Q).Rep;
                Remainders.add(P.Int);
 
                //checking for duplicate remainders
 
                comparison:
                for (int i =0; i< Remainders.size(); i++){
                    for (int j=i+1; j<Remainders.size(); j++){
                        if (Arrays.equals(Remainders.get(i),Remainders.get(j))){
                            duplicate = false;
                            //it seems that I cannot recall integer from for loop so I will store this as pass-by-reference item
                            detector_1[0]=i;
                            detector_2[0]=j;
                            break comparison ;
                        }
                        else{
                            duplicate = true;
                        }
                    }
         
                }
                index ++;
                }
                //counter tells you the value of exponent in other words number of remainders
                //index tells you where the end of NonRep part of fraction is
                Number output = new Number();
                output.Int= this.Int;
                // Now storing NonRep size with what we got with detectors
                output.NonRep =new short[detector_1[0]+1];
                //-1 because it has the first of duplicated z included
                output.Rep= new short[detector_2[0]-detector_1[0]];
                //collecting all remainders
                //For NonRep
                //-1 because it has the first of duplicated z included
                for (int j= output.NonRep.length-1; j>= 0 ; j--){
                //since our print method prints backwards
                    output.NonRep[output.NonRep.length-1- j] = ratio.get(j);
                }
                //For i-1 since the beggining of new repeat is included
 
                for (int i= detector_1[0] ; i<ratio.size()-2; i++){
                    output.Rep[ratio.size()-detector_1[0]-i] = ratio.get(i);
                }
                return output;
            }
       
            public Number add(Number input){
   
  
                //Check for runtime error
                if (input.Int == null || Int == null || input.Int.length == 0 || Int.length == 0) {
                    return null; //This is undefined so return null
                }
  
                Number sum = new Number();
                sum.Base = Base; //Sum base is the same as input numbers
                  
                //Initialize sum's array
                sum.NonRep = new short[0];
                sum.Rep = new short[0];             
                int overlapping_length; //Describes the length of the overlapping region of two number arrays
                int trailing;
                if (Int.length >= input.Int.length) {
                    overlapping_length = input.Int.length;
                    trailing = Int.length - input.Int.length;
                    sum.Int = new short[Int.length + 1];
                } else {
                    overlapping_length = Int.length;
                    trailing = input.Int.length - Int.length;
                    sum.Int = new short[input.Int.length + 1];
                } 
  
                //Add two numbers only for the overlapping region and store the results into sum's array
                short carry = 0;
                int sum_index = 0;
                int this_index = 0;
                int input_index = 0;
                for (int i = 0; i < overlapping_length; i++) {
                    sum.Int[sum_index] = (short) ((Int[this_index] + input.Int[input_index] + carry) % sum.Base); 
                    carry = (short) ((Int[this_index] + input.Int[input_index] + carry) / sum.Base);
                    sum_index++;
                    this_index++;
                    input_index++;
                }
                  
                //Address the edge cases for carry
                if (Int.length > input.Int.length) {
                    for (int i = 0; i < trailing; i++) {
                        sum.Int[sum_index] = (short) ((Int[this_index] + carry) % sum.Base); 
                        carry = (short) ((Int[this_index] + carry) / sum.Base);
                        sum_index++;
                        this_index++;   
                    }
                } else if (Int.length < input.Int.length) {
                    for (int i = 0; i < trailing; i++) {
                        sum.Int[sum_index] = (short) ((input.Int[input_index] + carry) % sum.Base); 
                        carry = (short) ((input.Int[input_index] + carry) / sum.Base);
                        sum_index++;
                        input_index++;
                    }
                }
  
                //add the last last carry
                sum.Int[sum_index] = carry;
                return sum;
            }
            //gets rid of trailing zeroes to be passed on to other methods
           public Number trim(){
               short counter =0;
               for(int i = this.Int.length -1; i>=0; i --){
                   if(this.Int[i] ==0){
                       counter++;
                   }
                   else {
                       break;
                   }
                 
               }
               Number output = new Number();
               output.Int= new short[this.Int.length-counter];
               for (int i = output.Int.length -1 ; i>= 0 ; i--){
                   output.Int[i] = this.Int[i];
               }
               output.Base=this.Base;
               output.NonRep=this.NonRep;
               output.Rep=this.Rep;
               return output;
           }
             
             
            //Difference is just adding a negative. Also it has been told that we will not be dealing with negative differences
            //however I just implemented a way of dealing with negatives anyway
 
              
            public Number subtract(Number input){
                //before timing consider case a-0; consider cases 0-0
                if(input.Int.length==0 || this.Int.length==0){
                    Number zerocheck = new Number();
                    zerocheck.Int =new short[1];
                    zerocheck.Int[0]=0;
                    zerocheck.NonRep = new short[1];
                    zerocheck.Rep = new short[1];
                    zerocheck.Rep[0] = 0; 
                    zerocheck.Base=this.Base;               
                   return zerocheck;
                }
                short zero_check = 1;
                for(int i=0;i<input.Int.length;i++){
                    zero_check+=input.Int[i];
                }
                if (zero_check == 1){
                    return this;
                }
                zero_check = 1;
                for(int i=0;i<this.Int.length;i++){
                    zero_check+=this.Int[i];
                }
                if (zero_check == 1){
                    Number zerocheck = new Number();
                    zerocheck.Int =new short[this.Int.length];
                    zerocheck.NonRep = new short[0];
                    zerocheck.Rep = new short[0];
                    zerocheck.Base=this.Base;
                    for (int k=0; k<input.Int.length; k++){
 
                        zerocheck.Int[k]=(short)(input.Int[k]*-1);
 
                    }
                    return zerocheck;
                }
                 
                 
                //trim front zeroes
                input=input.trim();
                Number trimmed= new Number();
                trimmed = this.trim();
                Number difference = new Number();
                difference.NonRep = new short[0];
                difference.Rep = new short[0];
                difference.Base=trimmed.Base;
                Number tmp = new Number();
                Number tmp2 = new Number();
                tmp.Int = new short[trimmed.Int.length];
                tmp2.Int = new short[input.Int.length];
                tmp.Base=trimmed.Base;
                tmp2.Base= trimmed.Base;
                //temporary storage for recursion
                for(int i = 0; i<tmp.Int.length; i++){
                    tmp.Int[i]= trimmed.Int[i];
                }
                for(int i = 0; i<tmp2.Int.length; i++){
                    tmp2.Int[i]= input.Int[i];
                }
                //implementing length
                if (this.Int.length >= input.Int.length){
                    difference.Int = new short[trimmed.Int.length];
                    for(int i = 0; i<difference.Int.length; i++){
                        difference.Int[i]=0;
                    }
                }
                else {
                    difference.Int = new short[input.Int.length];
                    for(int i = 0; i<difference.Int.length; i++){
                        difference.Int[i]=0;
                    }
                }
                //trim input and this.Number
 
                //standard subtraction algorithm
                //Probably very inefficient code since there is duplicate array of difference (input) but had to do so to consider variety of cases
                  
                for (int i = 0; i < difference.Int.length; i++) {
 
                    // if input.Int.length is for sure greater, then it gives negative difference
                    if (input.Int.length > trimmed.Int.length){
                        difference = tmp2.subtract(tmp);
                        for (int k=0; k<=difference.Int.length-1; k++){
                            difference.Int[k]=(short)(difference.Int[k]*-1);
  
                        }
                        return difference;
                    }
 
                    //standard subtraction up to second significant digit
                    if (i<=difference.Int.length-2){
                         
                    //consider 3  cases : input.length < this.length , input.length = this .length, input.length=this.length but results in minus
                        if(trimmed.Int.length > input.Int.length){
                            if(i< input.Int.length){
                                if(trimmed.Int[i]> input.Int[i]){
                                difference.Int[i]= (short)(trimmed.Int[i] - input.Int[i]+difference.Int[i]);
                                    }
           
                                else if (i<trimmed.Int.length-1&&trimmed.Int[i]+difference.Int[i]< input.Int[i]) {
                                difference.Int[i]= (short)(trimmed.Int[i]- input.Int[i]+difference.Int[i] + trimmed.Base);
                                difference.Int[i+1]= (short) (difference.Int[i+1]-1);
                                }
                            }
 
                            //cases where input length is smaller than this length
                                else if (i<trimmed.Int.length-1 && trimmed.Int[i] == 0) {
                                difference.Int[i]= (short)(trimmed.Int[i]+difference.Int[i] + trimmed.Base);
                                difference.Int[i+1]= (short) (difference.Int[i+1]-1);
                                }
                                else if (i<trimmed.Int.length-1 && trimmed.Int[i] != 0){
                                     difference.Int[i]= (short)(trimmed.Int[i] +difference.Int[i]);
                                }
                        }
                        else if (trimmed.Int.length<= input.Int.length){
                            if(trimmed.Int[i]> input.Int[i]){
                                difference.Int[i]= (short)(trimmed.Int[i] - input.Int[i]+difference.Int[i]);
                                }
   
                            else if (i<trimmed.Int.length-1&&trimmed.Int[i]< input.Int[i]) {
                                difference.Int[i]= (short)(trimmed.Int[i]- input.Int[i]+difference.Int[i] + trimmed.Base);
                                difference.Int[i+1]= (short) (difference.Int[i+1]-1);
                                }
                        }
                    }
                     
                    //Final digit subtraction for case 2
                    if (i==input.Int.length-1 && input.Int.length == trimmed.Int.length){
                        difference.Int[i]= (short)(difference.Int[i]- input.Int[i]+trimmed.Int[i]);
                    }
                    //below if statement takes care of negative difference that arises from same number of digits (case 3)
                    if(i == difference.Int.length-1 && input.Int.length == trimmed.Int.length&& difference.Int[i]<0){ 
                        difference = tmp2.subtract(tmp);
                        for (int j=0; j<=difference.Int.length-1; j++){
                            difference.Int[j]=(short)(difference.Int[j]*-1);
                        }
          
                        return difference;
                    }
                    if (i==difference.Int.length-1 && input.Int.length < trimmed.Int.length){
                        difference.Int[i]= (short)(trimmed.Int[i]+difference.Int[i]);
                    }
 
             
                }
            
                 
                return difference;
            }
                 
            //Below method is based on that we will not be dealing with negative divisor and divisor is not equal to 0
            //We use the notation where Number.Rep represents the remainder and the Number.Int represent the quotient.
            public Number division(Number input){
                //Below is if the divisor is greater than the dividend
                //since array is an address any method called on it will alter it's value. We only want to check if this is negative
                 //TRIM
                //trim front zeroes
                input=input.trim();
                Number trimmed= new Number();
                trimmed = this.trim();
                 
                 
                Number check_1 = new Number();
                check_1.Int= trimmed.Int;
                check_1.Base=trimmed.Base;
                check_1.Rep=trimmed.Rep;
                check_1.NonRep = trimmed.NonRep;
                Number check_2 = new Number();
                check_2.Int=input.Int;          
                check_2.Base=trimmed.Base; 
                check_2.Rep=trimmed.Rep;
                check_2.NonRep = trimmed.NonRep;
                 //check for a/a <1 and a/a =1
                if(!(check_1.subtract(check_2).checknegative())){
                    Number quotient = new Number();
                    quotient.Base=trimmed.Base; 
                    quotient.Int= new short[1];
                    quotient.Rep= trimmed.Int;
                    quotient.NonRep=trimmed.NonRep;
                    quotient.Int[0] =0       ;                  
                   return quotient;
                }
                if(Arrays.equals(check_1.Int,check_2.Int)){
                    Number quotient = new Number();
                    quotient.Base=trimmed.Base; 
                    quotient.Int= new short[1];
                    quotient.Rep= new short[1];
                    quotient.Rep[quotient.Rep.length-1] = 0;
                    quotient.NonRep=trimmed.NonRep;
                    quotient.Int[quotient.Int.length-1]= 1;                  
                   return quotient;
                }
                if(input.Int.length==0 || this.Int.length==0){
                    Number quotient = new Number();
                    quotient.Base=trimmed.Base; 
                    quotient.Int= new short[1];
                    quotient.Rep= new short[1];
                    quotient.Rep[quotient.Rep.length-1] = 0;
                    quotient.NonRep=trimmed.NonRep;
                    quotient.Int[quotient.Int.length-1]= 0;                  
                   return quotient;
                }
                 
                //first creating a temporary storage that stores the same amount digits as the divisor
                ArrayList<Short> tmp=new ArrayList<Short>();
                Number tmp_array = new Number();
                tmp_array.Base=trimmed.Base;   
                tmp_array.Int= new short[input.Int.length];
                //the maximum possible digit for quotient above, for arraylist this is not necessary but just wrote it down for safety
                ArrayList<Short> quotient = new ArrayList<Short>(trimmed.Int.length - input.Int.length+1);
                //Division is a pain with array, we will use arraylist instead and store everything into Number in the end
                int index=trimmed.Int.length-input.Int.length;
                for (int i = trimmed.Int.length -input.Int.length;i>=0; i--){
                      
                    //the below has messy index but this had to be to align the arrays and arraylist
                    if(i==trimmed.Int.length -input.Int.length){
                        //44/32 (same digit division made easier)
                    for (int j= trimmed.Int.length -1; j>= i; j--){
                        tmp_array.Int[j-i]=trimmed.Int[j];
                        tmp.add((trimmed.Int[j]));
                    }
                    }
                    else{
                        tmp_array.Int = new short[tmp.size()];
                    for(int l=0; l<tmp.size(); l++){
                        tmp_array.Int[tmp_array.Int.length -1- l] = tmp.get(tmp.size()-l-1);
                    }
                     
                    }
                    //subtraction
                    int counter=0;
                    while(counter< trimmed.Base && (tmp_array.subtract(input)).checknegative()){
                        tmp_array=tmp_array.subtract(input);
                        counter++;
                    }
                    //
                    tmp.clear();
     
                    for(int k=0; k<tmp_array.Int.length; k++){
                         tmp.add(tmp_array.Int[k]);
                    }
 
                    if(index >0 ){
                    tmp.add(0,trimmed.Int[index-1]);
                    quotient.add((short) counter);
                    // on the last index there is no digit, so we use below to avoid nullpoint
                    index--;
                    }
                    else if (index ==0) {
                         quotient.add((short) counter);
                    }    
            }
                Number output= new Number();
                 //Below are just space fillers and considering that print method prints backwards
                output.Int= new short[quotient.size()];
                for(int i = quotient.size()-1; i>=0; i--){
                    output.Int[i] = quotient.get(quotient.size()-1-i);
                }
                output=output.trim();
                output.Base=trimmed.Base;
                output.Rep= new short[tmp_array.Int.length];
                //last case
                //left over treated as remainder
                tmp_array=tmp_array.trim();
                if (tmp_array.Int.length==0){
                    output.Rep = new short[1];
                    output.Rep[0] = 0;
                }
                else {
                    output.Rep= tmp_array.Int;//.lowestform();
                }
                output.NonRep= trimmed.NonRep;
                return output;
            }
            //I was hesitant to make another method as it increases the run time and makes the code inefficient but writing this method
            //would be much easier
            public boolean checknegative(){
                for (int i=0; i < this.Int.length; i++){
                    if (this.Int[i] < 0 ){
                        return false;
                    }
                }
                return true;
      
            }
            /*public short[] lowestform(){
                Boolean A=true;
                while (A) {
                    for(int i=this.Int.length-1;i>=0; i--){
                        if (this.Int[i] >= Base){
                            this.Int[i]-= Base;
                            this.Int[i+1]++;
                        }
                    }
                    for (int j =this.Int.length-1; j>=0; j--){
                        if(this.Int[j]>Base){
                            A=true;
                        }
                        else{
                            A=false;
                        }
                    }
                }
                return this.Int;
            }*/
            // I used the pseudocode and turned it into java code
            public Number multiply(Number input){
                Number product= new Number();
                product.Int= new short[input.Int.length+this.Int.length+1];
                product.Base = this.Base;
                product.NonRep = new short[0];
                product.Rep = new short[0];
                short[][] tmp = new short[input.Int.length][this.Int.length + input.Int.length +1 ];
                for( int j = 0; j< input.Int.length; j++){
                    short carry = 0;
                    for ( int i=0; i<this.Int.length; i++){
                        short prod = (short) (this.Int[i] * input.Int[j] + carry);
                        tmp[j][i+j] = (short) (prod % this.Base);
                        carry = (short) ( prod/this.Base);
                    }
                    tmp[j][this.Int.length+j ]= carry;
                }
                short carry = 0;
                for (int i =0; i < input.Int.length + this.Int.length; i++){
                    int sum=carry;
                    for (int j=0; j<input.Int.length; j++ ){
                        sum = (short)(sum + tmp[j][i]);
                    }
                    product.Int[i] = (short)(sum % this.Base);
                    carry = (short)(sum / this.Base);
                }
                product.Int[input.Int.length + this.Int.length]= carry;
                return product.trim();
            }
          
        //=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
              
            public void printShortArray(short[] S) {
                for (int i = S.length-1; i>=0; i--) {
                    System.out.print(S[i]+ " ");
                    //It is hard to tell if the answer is correct if base> 10. so there is space between each digit
                }
            } 
  
            public void printNumber(Number N) {
                System.out.print("(");
                N.printShortArray(N.Int);
                System.out.print(".");
                N.printShortArray(N.NonRep);
                System.out.print("{");
                N.printShortArray(N.Rep);
                System.out.print("})_");
                System.out.println(N.Base);
            }
            short Base; short[] Int,NonRep,Rep;
        }
  
      
        /*Number N1=new Number() ;
        N1.Base=10; N1.Int=new short[2]; N1.NonRep=new short[3];
        N1.Int[1]=1; N1.Int[0]=9;
        N1.NonRep[2]=2; N1.NonRep[1]=4; N1.NonRep[0]=7;
        N1.Rep=new short[0];
        N1.printNumber(N1);
  
        Number N2=new Number() ;
        short R=2;
        N2=N1.convert(N1,R);
        N2.printNumber(N2);*/
  
        Number N1  = new Number();
        N1.Base = 10;
        N1.Int = new short[]{5,7,3};
        N1.NonRep=new short[0];
        N1.Rep=new short[0];
        N1.printNumber(N1);
  
        Number N2 = new Number();
        N2.Base = 10;
        N2.Int = new short[]{9,1};
        N2.NonRep=new short[]{7,4,2};
        N2.Rep=new short[]{};
        N2.printNumber(N2);
 
// final conversion sums everything up!!!!
      //  Number N5 = N2.finalconversion(N2,(short) 2);
      //  N5.printNumber(N5);
//        Number N7 = N1.convert(N1, (short) 2) ;
  //      N7.printNumber(N7);
        Number N6 = N2.finalconversion(N2, (short) 2);
        N6.printNumber(N6);
    ;
 
    }       
  
}