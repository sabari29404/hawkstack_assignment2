package hawkstack_dsa_project2;

public class first_non_repeating_character {
	public static void main(String[] args) {
		/*		
			Problem: Return the first non-repeating character in a string.
			Example: 'aabbcde' → 'c'
			If none exist, return null.

		*/		
				String a = "aabbcde";
					
				if(chk(a)==0) {
					System.out.println("null");
				}
				else {
					System.out.println("first not repeating character: "+chk(a));
				}
			}
			
			public static char chk(String a) {
				
				for(int i=0; i<a.length(); i++) {
					char ch=a.charAt(i);
					
					if(a.indexOf(ch)==a.lastIndexOf(ch)) {
						return ch;
					}
				}
				
				return 0;
	}
}
