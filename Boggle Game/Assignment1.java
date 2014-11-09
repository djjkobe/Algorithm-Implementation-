import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Assignment1 {

	private static StringBuilder StringBuilder =new StringBuilder();
	private static StringBuilder gameone =new StringBuilder();
	 private static boolean[][] visited;
	 private static DictInterface a;
	 private static DictInterface gameDictionary;
	public static void main(String[] args) throws IOException {
		//decide which implementation to use
	
		if(args.length==0)
		{
			gameDictionary=new DLB();
			a=new DLB();
		}
		else 
		{
			if(args[0].equals("0"))
			{
				gameDictionary=new MyDictionary();
				a=new MyDictionary();	
			}
			else
			{
			gameDictionary=new DLB();
			a=new DLB();
			}
		}
		//form the dictionary list=>a
		BufferedReader input = new BufferedReader(new FileReader("dictionary.txt"));
		String n=null;
		while((n = input.readLine())!=null)
		{
			 a.add(n);
		}
		 input.close();
		 //form the game board
		ArrayList<String> result=new ArrayList<String>();
		BufferedReader input1 = new BufferedReader(new FileReader("data4.txt"));
		String x;
		
		
		while((x= input1.readLine())!=null)
		{
			String preboard[]=x.split("");
			//System.out.println(preboard.length);
			game newGame =new game(preboard.length);
			int N =(int)newGame.boardSize;
			String board[] []=new String [N][N];
			int number =1 ;
			for (int m = 0;m<N;m++)
			{
				for (int k=0;k<N;k++)
				{
					board[m][k]=preboard[number];
					number++;
					System.out.print(board[m][k]+" ");
				}
				System.out.println();
			}
			visited = new boolean[N][N];
			//start to find all the corrent words in the board and put the in the arraylist "result". 
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{	
					search(N,board,StringBuilder.append(""), i, j,result);
					StringBuilder =new StringBuilder();	
				}
			}
			Collections.sort(result);
		//	System.out.println(result.size());
			ArrayList<String> userguess =new ArrayList<String>();
			for (int i=0;i<result.size();i++)
			{	
				gameDictionary.add(result.get(i));
			}
			System.out.println("Welcome to the game!");
			System.out.println("This board has "+result.size()+" valid words. Enjoy the game!");
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Please enter a word or enter 'E' to exit");
			String myint = keyboard.nextLine();
			while(!myint.equals("E"))
			{	
				gameone.append(myint);
				if((gameDictionary.searchPrefix(gameone)==0)||(gameDictionary.searchPrefix(gameone)==1))
				{
					System.out.println("This word is not valid, please try again");
					gameone=new StringBuilder();
				}
				else
				{
					if(userguess.contains(gameone.toString()))
					{
						System.out.println("You already made this guess, please try again!");
						gameone=new StringBuilder();
					}
					else	
					{
						System.out.println("This word is valid, nice try!");
						System.out.println("Please enter a word or enter 'E' to exit");
						userguess.add(gameone.toString());
						gameone=new StringBuilder();
					}
				}
				myint = keyboard.nextLine();
				if(userguess.size()==result.size())
				{
					break;
				}
			}
			if(myint.equals("E"))
			{
				System.out.println("All the words are :");
				Collections.sort(userguess);
				for(int i=0;i<result.size();i++)
				{
					System.out.println(result.get(i));
				}
				if(userguess.size()==0)
				{	
					System.out.println("Sorry, but you didn't make any correct guess!");
				}
				else
				{
					System.out.println("You correctly guessed "+userguess.size()+" words which are :" );
				}
				for(int k=0;k<userguess.size();k++)
				{
					System.out.println(userguess.get(k));
				}
				System.out.println("the percentage of the total words that were guessed is :");
				double answer1 =(double)userguess.size();
				double answer2 =(double)result.size();	
				double answer =((answer1) / (answer2));
				System.out.println(answer);
			}
			System.out.println("Let's see if there is another board to play!");
			System.out.println();
			StringBuilder =new StringBuilder();	
			result=new ArrayList<String>();
		}
		System.out.println("Sorry there is no more boards. Thanks for playing the game!");
		 input1.close();
        }

	
	
	 public static void search(int N,String[][] board,StringBuilder prefix, int i, int j,ArrayList<String>result) throws IOException {
	        if (i < 0 || j < 0 || i >= N || j >= N) return;
	        // can't visited a cell more than once
	        if (visited[i][j])
	        {
	        	return;
	        }
			if(board[i][j].equals("*"))
			{
				for (char q='a';q<='z';q++)
				{
					prefix = prefix.append(Character.toString(q).toLowerCase());
					 if (a.searchPrefix(prefix)==0)
					 {
						 	
						 prefix.delete(prefix.length()-1, prefix.length());
					 }
					 else if (a.searchPrefix(prefix)==2) 
					 {
						 if( (!result.contains(prefix.toString()))&&prefix.length()>=3)
						 {
							 result.add(prefix.toString());
				        		
						 }
						 prefix.delete(prefix.length()-1, prefix.length());
						 visited[i][j] = false;
				        	
					 }
					 else
					 {
						 if (a.searchPrefix(prefix)==3) 
						 {
							 if ((!result.contains(prefix.toString()))&&prefix.length()>=3)
							 {
								 result.add(prefix.toString());
				        		
							 }
				        	
						 }
						 for (int ii = -1; ii <= 1; ii++)
						 {
							 for (int jj = -1; jj <= 1; jj++)
							 {
								 if(jj!=0||ii!=0)
								 {
									 visited[i][j] = true;
									 search(N,board,prefix, i + ii, j + jj,result);	 
								 }
							 }
						 }
						 visited[i][j]=false;
						 prefix.delete(prefix.length()-1, prefix.length());
						 
					 }  
				}   			
				return;
			}
			else
			{
				prefix = prefix.append(board[i][j].toLowerCase());
				if (a.searchPrefix(prefix)==0)
				{
					prefix.delete(prefix.length()-1, prefix.length());
					return;
				}
				visited[i][j] = true;
				if (a.searchPrefix(prefix)==2) 
				{
	        	
					if( (!result.contains(prefix.toString()))&&prefix.length()>=3)
					{
						result.add(prefix.toString());
					}
					prefix.delete(prefix.length()-1, prefix.length());
					visited[i][j] = false;
					return;
				}
				if (a.searchPrefix(prefix)==3) 
				{
					if ((!result.contains(prefix.toString()))&&prefix.length()>=3)
					{
						result.add(prefix.toString());    		
					}
				}
				for (int ii = -1; ii <= 1; ii++)
				{
					for (int jj = -1; jj <= 1; jj++)
					{
						if(jj!=0||ii!=0)
						{
							search(N,board,prefix, i + ii, j + jj,result);
						}
					}
	           
				}
				visited[i][j]=false;
				prefix.delete(prefix.length()-1, prefix.length());
			}    
	       
	 	}
	
}



	
		




