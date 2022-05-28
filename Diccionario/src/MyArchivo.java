import java.io.File;
import java.util.*;


public class MyArchivo {
	File file = new File("C:\\text.txt");
	
	public Boolean Existe() {
		return file.exists();
	}

	public void Writing(Vector<Palabra> read){
		//Scanner sobre el archivo.
		file.delete();
		try {
			Formatter sc = new Formatter(file);
			
			String s="";
			String info="";
			for(int i =0;i<read.size();i++) {
				s=read.get(i).s;
				info = read.get(i).info;
				info=info.trim();
				sc.format("%s %s", s,info+"\n");
			}
			sc.close();
		}catch (Exception e) {
			System.out.println("Ya no jodas mas");
		}
		
		return;
	}
	
	public Vector<Palabra> Reading(){
		Vector<Palabra> read =new Vector<Palabra>();
		//Scanner sobre el archivo
		try {
			Scanner sc = new Scanner(file);
			Boolean palabrita = true;
			
			String s="";
			String info="";
			while(sc.hasNext()) {
				if(palabrita == true)
					s=sc.next();
				else
					info=sc.nextLine();
					
				if(palabrita==false) {
					read.add(new Palabra(s,info));
					s="";
					info="";
				}
				palabrita = !palabrita;
			}
			
			sc.close();
		}catch (Exception e) {
			System.out.println("Ya no jodas mas");
		}
		
		return read;
	}
}
