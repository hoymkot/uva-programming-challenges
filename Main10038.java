package backlog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main10038 {
	
	public static void main (String [] abc) throws IOException  {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String [] numStr=s.split(" ");
			//Ignore the first one.
			int [] num=new int [numStr.length-1];
			for (int i=0;i<num.length;i++) {
				num[i]=Integer.parseInt(numStr[i+1]);
			}

			boolean [] taken=new boolean [num.length];
			boolean foundNotTaken=false;
			
			for (int i=0;i<num.length-1;i++) {
				int diff=Math.abs(num[i]-num[i+1]);
				if (diff<num.length) {
					taken[diff]=true;
				} else {
					foundNotTaken=true;
					break;
				}
			}
			
			if (!foundNotTaken) {
				for (int i=1;i<taken.length;i++) {
					if (!taken[i]) {
						foundNotTaken=true;
						break;
					}
				}
			}
			
			if (foundNotTaken) {
				System.out.println("Not jolly");
			} else {
				System.out.println("Jolly");
			}
		}
	}
}