package A4;

import java.util.*;



//import A4.sds.HiRiQ;

public class assignment4 {

	static class HiRiQ {
		//int is used to reduce storage to a minimum...
		public int config;
		public byte weight;

		//initialize to one of 5 reachable START config n=0,1,2,3,4
		HiRiQ(byte n) {
			  if (n==0)
			   {config=65536/2;weight=1;}
			  else
			    if (n==1)
			     {config=1626;weight=6;}
			    else
			      if (n==2)
			       {config=-1140868948; weight=10;}
			      else
			        if (n==3)
			         {config=-411153748; weight=13;}
			        else
			         {config=-2147450879; weight=32;}
		}

		boolean IsSolved()
		{
		  return( (config==65536/2) && (weight==1) );
		}

		//transforms the array of 33 booleans to an (int) config and a (byte) weight.
		public void store(boolean[] B)
		{
		int a=1;
		config=0;
		weight=(byte) 0;
		if (B[0]) {weight++;}
		for (int i=1; i<32; i++)
		{
		if (B[i]) {config=config+a;weight++;}
		a=2*a;
		}
		if (B[32]) {config=-config;weight++;}
		}

		//transform the int representation to an array of booleans.
		//the weight (byte) is necessary because only 32 bits are memorized
		//and so the 33rd is decided based on the fact that the config has the
		//correct weight or not.\

		public boolean[] load(boolean[] B)
		{

		  byte count=0;
		int fig=config;
		B[32]=fig<0;
		if (B[32]) {
		  fig=-fig;
		  count++;
		  }
		int a=2;
		for (int i=1; i<32; i++) 
		{
		B[i]= fig%a>0;
		if (B[i]) {
		   fig=fig-a/2;count++;
		   }
		a=2*a;
		}
		B[0]= count<weight;
		return(B);
		}

		//prints the int representation to an array of booleans.
		//the weight (byte) is necessary because only 32 bits are memorized
		//and so the 33rd is decided based on the fact that the config has the
		//correct weight or not.
		public void printB(boolean Z)
		{if (Z) {System.out.print("[ ]");} else {System.out.print("[@]");}}

		public void print()
		{
		byte count=0;
		int fig=config;
		boolean next,last=fig<0;
		if (last) {fig=-fig;count++;}
		int a=2;
		for (int i=1; i<32; i++)
		{
		next= fig%a>0;
		if (next) {fig=fig-a/2;count++;}
		a=2*a;
		}
		next= count<weight;

		count=0;
		fig=config;
		if (last) {fig=-fig;count++;}
		a=2;

		System.out.print("      ") ;
		printB(next);
		for (int i=1; i<32; i++)
		{
		next= fig%a>0;
		if (next) {
		   fig=fig-a/2;count++;
		   }
		a=2*a;
		printB(next);
		if (i==2 || i==5 || i==12 || i==19 || i==26 || i==29) {
		   System.out.println() ;
		   }
		if (i==2 || i==26 || i==29) {
		   System.out.print("      ") ;
		   }
		}
		printB(last); System.out.println() ;
		}
	}

	public static boolean checksolution(boolean [] A){
		HiRiQ n0=new HiRiQ((byte) 0) ;
		boolean [] solution = new boolean[33];
		solution=n0.load(new boolean [33]);
		for (int i=0; i<33; i++){
			if(A[i]!=solution[i]){
				return false;
				}
		}
		return true;
	}

	public static boolean colouranalysis(boolean[] A){
		int B=0;
		int Y=0;
		int R=0;
		boolean x = false;
		boolean y = false;
		for (int i=0;i<33;i++){
		//count for number of yellow
		if(A[i]==true && (i==1||i==3||i==7||i==10||i==13||i==16||i==19||i==22||i==25||i==29||i==31)){
			Y++;
		}
		else if(A[i]==true && (i==0||i==5||i==6||i==9||i==12||i==15||i==18||i==21||i==24||i==28||i==30)){
			B++;
		}

		else if(A[i]==true && (i==2||i==4||i==8||i==11||i==14||i==17||i==20||i==23||i==26||i==27||i==32)){
			R++;
		}
		}
		if (((B%2)==(R%2))&&((B%2)!=(Y%2))){
			x= true;
		}
		//colour analysis tells us that single white pixel must be of form (0,1,0)
		if (Y+B+R==1&& !(B==0&& Y==1 && R==0)){
			//only 5 cells are yellow in both the original colouring and the flipped one. These are only 5 configurations with a single white pixel that are reachable from the solved state
			if(!(A[1]||A[13]||A[16]||A[19]||A[31])){
			x= false;
			}
		}
		//(0,5,0) no such configuration reachable
		if (Y+B+R==5&& (B==0 && Y==5 && R==0)){
			x= false;
			
		}
		if (x==false){
			x=false;
		}
	
		//re-initialize for symmetry
		B=0;
		Y=0;
		R=0;
		for (int i=0;i<33;i++){
			if(A[i]==true && (i==1||i==5||i==11||i==8||i==13||i==16||i==19||i==21||i==24||i==27||i==31)){
				Y++;
			}
			else if(A[i]==true && (i==2||i==3||i==6||i==9||i==12||i==14||i==17||i==22||i==25||i==28||i==32)){
				B++;
			}

			else if(A[i]==true && (i==0||i==4||i==7||i==10||i==15||i==18||i==20||i==23||i==26||i==29||i==30)){
				R++;
		}
		}
		
		
		if ((B%2)==(R%2)&&(B%2)!=(Y%2)){
			y= true;
		}
		//colour analysis tells us that single white pixel must be of form (0,1,0)
		if (Y+B+R==1&& !(B==0&& Y==1 && R==0)){
			//only 5 cells are yellow in both the original colouring and the flipped one. These are only 5 configurations with a single white pixel that are reachable from the solved state
			if(!(A[1]||A[13]||A[16]||A[19]||A[31])){
			y= false;
		}
		}
		//(0,5,0) no such configuration reachable
		if (Y+B+R==5&& (B==0 && Y==5 && R==0)){
			y= true;
			
		}
		if (y==false){
			y=false;
		}
		
		
		boolean z= (x&&y);
//		System.out.println(z);
		//also add one more condition 
		return z;
		
		
	}
			
	//we let 1=[0,1,2] 2=[3,4,5] ... and son on according to assignment layout
	public static ArrayList<String> validsubstitution(boolean[] B){
		ArrayList<String> possibility = new ArrayList<>();
		//38 different configurations
		//first with horizontals labeled from 1 to 19
		boolean[] tmp = new boolean[33];
		for (int i =0; i<33; i++){
			tmp[i] = B[i];
		}
		
		if (!(B[0]==B[2])){
			if(B[1]){
			possibility.add("2W0");
			}
			else if (!B[1]){
			possibility.add("0B2");
			}
		}
		if (!(B[3]==B[5])){
			if(B[4]){
			possibility.add("5W3");
			}
			else if (!B[1]){
			possibility.add("3B5");
			};
		}
		//from 3 to 7 
		for (int i =0; i<5; i++){
			if(!(B[i+6]==B[i+8])){
				if(B[i+7]){
					possibility.add((i+8)+"W"+(i+6));
					}
					else if (!B[i+7]){
					possibility.add((i+6)+"B"+(i+8));
					}
			}
		}
		//from 8 to 12
		for (int i =0; i<5; i++){
			if(!(B[i+13]==B[i+15])){
				if(B[i+14]){
					possibility.add((i+15)+"W"+(i+13));
					}
					else if (!B[i+13]){
					possibility.add((i+13)+"B"+(i+15));
					}
			}
		}
		//from 13 to 17
		for (int i =0; i<5; i++){
			if(!(B[i+20]==B[i+22])){
				if(B[i+21]){
					possibility.add((i+22)+"W"+(i+20));
					}
					else if (!B[i+13]){
					possibility.add((i+20)+"B"+(i+22));
					}
			}
		}
		//from 18 to 19
		for (int i =0; i<2; i++){
			if (B[i+27]!= B[i+29]){
			if(B[i+28]){
				possibility.add((i+29)+"W"+(i+27));
				}
				else if (!B[i+28]){
				possibility.add((i+27)+"B"+(i+29));
				}
		}
		}
		//now we deal with 19 verticals
		if (!(B[12]==B[26])){
			if(B[19]){
				possibility.add(26+"W"+12);
				}
				else if (!B[19]){
				possibility.add(12+"B"+26);
				}
		}
		if (!(B[11]==B[25])){
			if(B[18]){
				possibility.add(25+"W"+11);
				}
				else if (!B[18]){
				possibility.add(11+"B"+25);
				}
		}
		if (!(B[2]==B[10])){
			if(B[5]){
				possibility.add(10+"W"+2);
				}
				else if (!B[5]){
				possibility.add(2+"B"+10);
				}
		}
		if (!(B[5]==B[17])){
			if(B[10]){
				possibility.add(17+"W"+5);
				}
				else if (!B[10]){
				possibility.add(5+"B"+17);
				}
		}
		if (!(B[10]==B[24])){
			if(B[17]){
				possibility.add(24+"W"+10);
				}
				else if (!B[17]){
				possibility.add(10+"B"+24);
				}
		}
		if (!(B[17]==B[29])){
			if(B[24]){
				possibility.add(29+"W"+17);
				}
				else if (!B[24]){
				possibility.add(17+"B"+29);
				}
		}
		if (!(B[24]==B[32])){
			if(B[29]){
				possibility.add(32+"W"+24);
				}
				else if (!B[29]){
				possibility.add(24+"B"+32);
				}
		}
		//next 5 groups
		if (!(B[1]==B[9])){
			if (B[4]){
			possibility.add(9+"W"+1);
			}
			else if (!B[4]){
			possibility.add(1+"B"+9);
			}
		}
		if (!(B[4]==B[16])){
			if (B[9]){
			possibility.add(16+"W"+4);
			}
			else if (!B[9]){
			possibility.add(4+"B"+16);
			}
		}
		if (!(B[23]==B[9])){
			if (B[16]){
			possibility.add(23+"W"+9);
			}
			else if (!B[16]){
			possibility.add(9+"B"+23);
			};
		}
		if (!(B[28]==B[16])){
			if (B[23]){
			possibility.add(16+"W"+28);
			}
			else if (!B[23]){
			possibility.add(28+"B"+16);
			};
		}
		if (!(B[23]==B[31])){
			if (B[28]){
			possibility.add(31+"W"+23);
			}
			else if (!B[28]){
			possibility.add(23+"B"+31);
			};
		}
		//next 5 groups
		if (!(B[0]==B[8])){
			if (B[3]){
			possibility.add(8+"W"+0);
			}
			else if (!B[3]){
			possibility.add(0+"B"+8);
			};
		}
		if (!(B[3]==B[15])){
			if (B[8]){
			possibility.add(15+"W"+3);
			}
			else if (!B[8]){
			possibility.add(3+"B"+15);
			};
		}
		if (!(B[8]==B[22])){
			if (B[15]){
			possibility.add(22+"W"+8);
			}
			else if (!B[15]){
			possibility.add(8+"B"+22);
			};
		}
		if (!(B[15]==B[27])){
			if (B[22]){
			possibility.add(27+"W"+15);
			}
			else if (!B[22]){
			possibility.add(15+"B"+27);
			};
		}
		if (!(B[30]==B[22])){
			if (B[27]){
			possibility.add(30+"W"+22);
			}
			else if (!B[27]){
			possibility.add(22+"B"+30);
			};
		}
		if (!(B[7]==B[21])){
			if (B[14]){
			possibility.add(21+"W"+7);
			}
			else if (!B[14]){
			possibility.add(7+"B"+21);
			};
		}
		if (!(B[6]==B[20])){
			if (B[13]){
			possibility.add(20+"W"+6);
			}
			else if (!B[13]){
			possibility.add(6+"B"+20);
			};
		}

			
		return possibility;
	}
		
	//this takes care of substitution	
	public static boolean[] substitution(boolean[] B, String n){
		int [][] triplets={{0,1,2},{3,4,5},{6,7,8},{7,8,9},{8,9,10},{9,10,11},
				{10,11,12},{13,14,15},{14,15,16},{15,16,17},
				{16,17,18},{17,18,19},{20,21,22},{21,22,23},{22,23,24},
				{23,24,25},{24,25,26},{27,28,29},{30,31,32},{12,19,26},
				{11,18,25},{2,5,10},{5,10,17},{10,17,24},{17,24,29},
				{24,29,32},{1,4,9},{4,9,16},{9,16,23},{16,23,28},{23,28,31},
				{0,3,8},{3,8,15},{8,15,22},{15,22,27},{22,27,30},{7,14,21},
				{6,13,20}};
		boolean[] tmp= new boolean[33];
		int x = 0,y = 0;
		for (int i =0 ; i<33 ;i++){
			tmp[i]= B[i];
		}
		if(n.length()==3){
		 x=n.charAt(0)-48;
		 y=n.charAt(2)-48;
		}
		if (n.length()==4){
			if(n.charAt(1)-48>9){
				x=n.charAt(0)-48;
				y=(n.charAt(2)-48)*10 + (n.charAt(3)-48);
			}
			if(n.charAt(2)-48>9){
				x=(n.charAt(0)-48)*10+(n.charAt(1)-48);
				y= (n.charAt(3)-48);
			}
		}
		else if (n.length()==5) {
			x=(n.charAt(0)-48)*10 + (n.charAt(1)-48);
			y=(n.charAt(3)-48)*10 + (n.charAt(4)-48);
		}
		
		//consider white subsitution
		if (x>y){
		for(int i=0; i<38;i++){
			if (triplets[i][0]==y && triplets[i][2]==x){
				tmp[triplets[i][0]]=!tmp[triplets[i][0]];
				tmp[triplets[i][1]]=!tmp[triplets[i][1]];
				tmp[triplets[i][2]]=!tmp[triplets[i][2]];
			}
		}
		}
		//consider black substitution
		if (x<y){

			for(int i=0;i<38;i++){
			if (triplets[i][0]==x && triplets[i][2]==y){
				tmp[triplets[i][0]]=!tmp[triplets[i][0]];
				tmp[triplets[i][1]]=!tmp[triplets[i][1]];
				tmp[triplets[i][2]]=!tmp[triplets[i][2]];
			}
			}
		}
		return tmp;
	}
	public static HiRiQ printsolution(boolean[] A, ArrayList<String> B){
		boolean[] C = new boolean[33];
		for (int i=0 ; i < B.size(); i++){
			C= substitution(A,B.get(i));
		}	
		HiRiQ tmp=new HiRiQ((byte) 1);
		tmp.store(C);
		return tmp;
	}

	public static void main(String[] args) {

		HiRiQ W=new HiRiQ((byte) 0) ;
		W.print(); System.out.println(W.IsSolved());
		HiRiQ X=new HiRiQ((byte) 1) ;
		X.print(); System.out.println(X.IsSolved());
		HiRiQ Y=new HiRiQ((byte) 2) ;
		Y.print(); System.out.println(Y.IsSolved());
		HiRiQ Z=new HiRiQ((byte) 3) ;
		Z.print(); System.out.println(Z.IsSolved());
		HiRiQ V=new HiRiQ((byte) 4) ;
		V.print(); System.out.println(Z.IsSolved());
//////////Change the HiRiQ to the one to be checked ** comment this out and pick your HiRiQ** /////////////////
		assignment4 aa= new assignment4();
		////some test boolean arrays ////
		boolean [] B={false, false, false, false, true, false, true, false, false, false, false, false, false, true, false, true, true, false, true, true, false, false, false, false, true, true, false, false, true, false, false, false, false};
		W.store(B);
		W.print();
		/////// some test boolean array ** comment this out and pick your HiRiQ**///////
		System.out.println(W.IsSolved());
		System.out.println(aa.solver(W));
//////////////////////Change the HiRiQ to the one to be checked ** comment this out and pick your HiRiQ** /////////////////
	}
	
	public void print(ArrayList<String> X){
		String string="";
		for (int i=0; i< X.size(); i++){
			string=string.concat(X.get(i) + " ");
		}
		System.out.println(string);
	}

	public static int difference(boolean [] A){
		HiRiQ solution = new HiRiQ((byte) 0);
		boolean[] C= new boolean[33];
		int count =0;
		for (int i=0; i<33 ; i++){
			if (A[i] != solution.load(C)[i]){
				count++;
			}
		}
		return count;
	}

	public class Configuration {

		int cost;
		String identification = new String();
		boolean [] board ;

		Configuration(int c, String id, boolean[] A) {
			cost = c;
			identification = id;
			board = A; 
		}

		public void setCost(int c) {
			cost = c;
		}

		public int getCost(){
			return cost;
		}

		public String getId() {
			return identification;
		}		
	}
	

	
	
	public static class SearchCandidates {
		LinkedList<Configuration> minQueue = new LinkedList<Configuration>();
		HashSet<Integer> collections = new HashSet<Integer>();

		public void addConfig(Configuration config) {
			if (minQueue.size() == 0) {
				minQueue.add(config);
				//collections.add(config.board);
			} else {
				int i = 0;
				//Make sure this minQueue is sorted in ascending order of cost by finding where the config object has to be inserted in the list
				while (i<minQueue.size() && config.cost > minQueue.get(i).cost) {
					i++;
				}
				minQueue.add(i, config);
				HiRiQ ssa = new HiRiQ((byte) 0);
				ssa.store(config.board);
				collections.add(ssa.config);
			}
		}

		public Configuration getMinConfig(){
			//Min config is gauranteed to be the first element in the list
			if (minQueue.size() > 0) {
				return minQueue.poll();	
			} else {
				return null;
			}
		}

		public int size(){
			return minQueue.size();
		}
	}

	public String solver(HiRiQ A){
		//initialize arraylist of arraylist of booleans (configurations)
		ArrayList<String> solutions = new ArrayList<String>();
		boolean[] s = new boolean[33];
		if ((colouranalysis(A.load(s))==false)){
			  System.out.println("IMPOSSIBRU!!");
			  return null;
		}
		// Inmplement search algorithm here
		
		// How to use PriorityQueue
		SearchCandidates minQueue = new SearchCandidates();
		boolean[] B = new boolean[33];
		String id = " ";
		//very first config

		Configuration config = new Configuration(0, id, A.load(B));
		
		//calling all possible substitution on A
		addNeighboursToMinQueue(minQueue, config);

		int iterations = 0;
		boolean solved = false;
		String solution = "";

		Configuration minConfig;
 		while (iterations < 100000 && !solved && minQueue.size() > 0) {
			//Dequeque from min priority queue

			minConfig = minQueue.getMinConfig();

			//Add its neighbours
			int x=addNeighboursToMinQueue(minQueue, minConfig);

			//Add this configuration id to present the steps to the solved case
			solutions.add(minConfig.identification);
			
			
			
			//SAVE TIME N white require N substitutions
			//CHECK IF THE CURRENT CONFIG IS THE SOLUTION
			HiRiQ empty = new HiRiQ ((byte) 0);
			empty.store(minConfig.board);
			if(white(minConfig.board)-1%x==0||iterations<=1){
				solved = checksolution(minConfig.board);	
			}

		
			
			if (solved) {
				solution = minConfig.getId();
				HiRiQ test= new HiRiQ((byte) 3);
				test.store(minConfig.board);
				test.print();
				System.out.println(test.IsSolved());
				}


			iterations++;
 		}
	    char toCheck = 'B';
	    char toCheck2 = 'W';
	    int count = 0;

	    for (char ch: solution.toCharArray()) { 
	        if (ch == toCheck || ch == toCheck2) {
	            count++;
	        }
	    }
 		if (solution.length()<14 && count == 2){
 			return "no move necessary";
 		}
 		
		return solution;
}

	public int addNeighboursToMinQueue(SearchCandidates minQueue, Configuration current){

		ArrayList<String> branch = validsubstitution(current.board);
		for (String s : branch) {
			Configuration neighbour = new Configuration(0,  current.getId() + " " +s, substitution(current.board, s));

			int x;
			if(s.contains("B")){
				x=2;
			}
			else{
				x=1;
			}
			//in odrer of priorty difference># white > type of substitution
			neighbour.setCost(heuristic(neighbour)+x+white(neighbour.board) );
			//if (white(current.board)>white(neighbour.board)){
			HiRiQ empty = new HiRiQ((byte) 0);
			empty.store(neighbour.board);
			//boolean present = minQueue.collections.add(empty);
			boolean aa = minQueue.collections.contains(empty.config);
			if(colouranalysis(neighbour.board)&&!(current.identification.equals(neighbour.identification))&&!aa){
			minQueue.addConfig(neighbour);
			}
		}
		//returns the depth of the search
	    char toCheck = 'B';
	    char toCheck2 = 'W';
	    int count = 0;

	    for (char ch: current.getId().toCharArray()) { 
	        if (ch == toCheck || ch == toCheck2) {
	            count++;
	        }
	    }
	    return count;
	}
	//heuristic calculation
	public static int heuristic(Configuration A){
		int x=0;
		x=difference(A.board);
		
		return x;		
	}
	//number of white pegs
	public static int white(boolean [] input){
		int count=0;
		for(int i=0; i< input.length; i++){
			if(input[i] == true){
			count++;
			}
		}
		return count;
	}

}





		
