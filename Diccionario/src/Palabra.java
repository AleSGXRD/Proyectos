import java.util.*;

public class Palabra  {
	String s;
	String info;
	long count = 0;
	
	public Palabra(String cad,String infor) {
		s=cad;
		info = infor;
		count= Hashing(s);
	}
	public long Hashing(String str) {
		int p= 53;
		int m=1000000009;
		long  hash_value =0;
		long  p_pow = 1;
		
		for(int i =0;i<str.length();i++){
			hash_value = (hash_value+(str.charAt(i)-'a'+1)*p_pow)%m;
			p_pow=(p_pow*p)%m;
		}
		return hash_value;
	}

	public long Hashinginv(String str) {
		int p= 53;
		int m=1000000009;
		long  hash_value =0;
		long  p_pow = 1;
		
		for(int i =str.length()-1;i>=0;i--){
			hash_value = (hash_value+(str.charAt(i)-'a'+1)*p_pow)%m;
			p_pow=(p_pow*p)%m;
		}
		return hash_value;
	}
}

class orden implements Comparator<Palabra>{
	@Override
	public int compare(Palabra o1,Palabra o2) {
		return o1.s.compareTo(o2.s);
	}
}
class ordenHash implements Comparator<Palabra>{
	@Override
	public int compare(Palabra o1,Palabra o2) {
		if (o1.count < o2.count) {
			return -1; 
		} 
		if (o1.count > o2.count) {
			return 1;
		}
		return 0;
	}
}
