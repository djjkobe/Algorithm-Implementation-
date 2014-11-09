
public class DLB implements DictInterface {
	
	private DLBnode root=new DLBnode("root");
	private DLBnode current =new DLBnode();
	private int i=0;
	private class DLBnode 
	{
		private String val;
    	private DLBnode rightSib;
    	private DLBnode child;
	
    	public DLBnode (String val)
    	{
    		this.val=val;
    		this.child=null;
    		this.rightSib=null;
    	}
    	public DLBnode ()
    	{
    		this.val=null;
    		this.child=null;
    		this.rightSib=null;
    	}
	}
	
	public boolean add(String s)
	{
		if(i==s.length())
		{
			if(current.val==null)
			{
				current.val="$";
				i=0;
				current=root.child;
				return true;
			}
			else if(current.rightSib==null)
			{
				DLBnode finish =new DLBnode("$");
				current.rightSib=finish;
				i=0;
				current=root.child;
				return true;
			}
			i=0;
			current=root.child;
			return false;
		}
		if(root.child==null)
		{
			root.child=current;
		}
		if(current.val==null)
		{
			current.val=Character.toString(s.charAt(i));
			DLBnode newone =new DLBnode();
			current.child=newone;
			current =current.child;
			i++;	
			return add(s);
		}
		else 
		{	
			if(current.val.charAt(0)==s.charAt(i))
			{	
				current=current.child;
				i++;
				return add(s);
			}
			else 
			{
				if(current.rightSib==null)
				{
					DLBnode temp=new DLBnode();
					current.rightSib=temp;
					current=temp;
					return add(s);
				}
				current=current.rightSib;
				return add(s);
			}
		}
	}
	public int searchPrefix(StringBuilder s)
	{
		DLBnode current =this.root.child;
		int i=0;
		while (current!=null)
		{
			if(current.val.charAt(0)==s.toString().charAt(i))
			{
				if(i==s.toString().length()-1)
				{
					if(current.child.val.equals("$")&&current.child.rightSib==null)
					{
						return 2;
					}
					if((current.child.val.equals("$")&&current.child.rightSib!=null)||!current.child.val.equals("$")&&current.child.rightSib!=null)
					{
						if(!current.child.val.equals("$")&&current.child.rightSib.val=="$")
						{
						return 3;
						}
						if(current.child.val.equals("$")&&current.child.rightSib!=null)
						{
							return 3;
						}
					}
					return 1;
				}
				current=current.child;
				i++;
			}
			else 
			{
				current=current.rightSib;
			}
		}
		return 0;	
	}
	public int searchPrefix(StringBuilder s, int start, int end)
	{
		return 0;
	}
}
