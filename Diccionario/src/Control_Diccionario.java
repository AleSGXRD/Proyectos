import java.util.*;
import java.io.File;

public class Control_Diccionario {
	
		public static void main(String[]args) {
			Scanner scan = new Scanner(System.in);
			Vector<Palabra> list= new Vector();
			
			MyArchivo file= new MyArchivo();
			
			Boolean exists = file.Existe();
				System.out.println("--------Bienvenido querido usuario--------");
			if(exists == true) {
				list = file.Reading();
			}else {
				System.out.println("Por favor para poder continuar almenos escriba 1 o mas palabras en nuestro diccionario.");
				System.out.println("------------------------------------------");
				list =  AddtoDic(list);
				list.sort(new ordenHash());
				file.Writing(list);
			}
			
			int option = 0;
			Boolean play=true;
			while(play) {
				System.out.println("------------------------------------------");
				System.out.println("¿Que operacion desea realizar?");
				System.out.println("1-Mostrar el listado del diccionario ordenado.");
				System.out.println("2-Buscar una palabra almacenada en el diccionario.");
				System.out.println("3-Eliminar una palabra almacenada en el diccionario.");
				System.out.println("4-Ver cuantos anagramas tiene una palabra entre las demas palabras del diccionario.");
				System.out.println("5-Calcular de cuantas maneras se puede escribir una palabra del diccionario.");
				System.out.println("6-Saber si una palabra del diccionario es palindromo.");
				System.out.println("7-Almacenar nuevos datos a su diccionario.");
				System.out.println("0-Cerrar el programa.");
				option = scan.nextInt();
				System.out.println("------------------------------------------");
				String cad="" ;
				int pos=0;
				switch(option) {
					case 1:
						ShowSort(list);
					break;
					case 2:
						System.out.println("¿Que palabra desea buscar?");
						cad = scan.next();
						pos = Search(cad,list);
						if(pos!=-1)
							Imprimir(list.get(pos));
							
					break;
					case 3:
						System.out.println("¿Que palabra desea eliminar?");
						cad = scan.next();
						pos = Search(cad,list);
						if(pos!=-1) {
							Eliminar(pos,list);
							list.sort(new ordenHash());
							file.Writing(list);
						}
							
					break;
					case 4:
						System.out.println("¿Que palabra ver sus diferentes anagramas existentes en el diccionario?");
						cad = scan.next();
						pos = Search(cad,list);
						if(pos!=-1)
							DifAnagrams(pos,list);
						break;
					case 5:
						System.out.println("¿Que palabra desea calcular la distintas maneras de escribirla?");
						cad = scan.next();
						pos = Search(cad,list);
						if(pos!=-1)
							calcManeras(list.get(pos));
						break;
					case 6:
						System.out.println("¿Que palabra desea saber si es palindromo?");
						cad = scan.next();
						pos = Search(cad,list);
						Boolean isP=false;
						if(pos!=-1) {
							isP=isPalindromo(list.get(pos));
						
							if(isP==true) 
								System.out.println("Esta palabra es Palindromo");
							else {
								System.out.println("Esta palabra no es Palindromo por lo que:");
								searchLongest(list.get(pos));
							}
						}
						break;
					case 7:
						list =  AddtoDic(list);
						list.sort(new ordenHash());
						file.Writing(list);
					break;
					case 0:
						play = false;
						break;
				}
			}
		}
		
		//Adicionar palabras al dicionario
		public static Vector<Palabra> AddtoDic(Vector<Palabra> list) {
			System.out.print("Tenga en cuenta que la sintaxis para añadir una palabra es:");
			System.out.println("(\"Palabra\" \"Significado\") \n(obviando las comillas)"
					+ " \nSe asume que si imprimes \"-\" como una palabra terminara la accion.");
			Scanner scanAdd = new Scanner(System.in);
			String s=scanAdd.next();
			String info=scanAdd.nextLine();
			info = info.trim();
			
			while(!s.equals("-")) {
				list.add(new Palabra(s,info));
				s=scanAdd.next();
				info=scanAdd.nextLine();
				info = info.trim();
			}
			//scanAdd.close();
			if(list.size()>0) {
				return list;
			}
			else {
				System.out.println("Debe añadir almenos una palabra por favor.");
				return AddtoDic(list);
			}
		}
		
		
		//Mostrar de manera ordenado
		public static void ShowSort(Vector<Palabra> list) {
			System.out.println("Listado ordenado");
			System.out.println();
			
			list.sort(new ordenHash());
			for(int i =0;i<list.size();i++) {
				System.out.print((i+1)+"- ");
				System.out.println(list.get(i).s);
			}
		}
		
		
		//Imprimir palabra
		public static void Imprimir(Palabra palabrita) {
			if(palabrita!=null)
				System.out.println(palabrita.s+": "+palabrita.info);
		}
		//Eliminar palabra
		public static Vector<Palabra> Eliminar(int pos,Vector<Palabra>list) {
			list.removeElementAt(pos);
			return list;
		}
		//Imprimir palabra
		public static void calcManeras(Palabra palabrita) {
			if(palabrita!=null){
				double cantd =Math.pow(2,palabrita.s.length());
				System.out.print("Esta palabra tiene unas ");
				System.out.printf("%.0f",cantd);
				System.out.println(" maneras distintas de escribirse");
			}
		}
		
		//Diferentes Anagramas
		public static void DifAnagrams(int pos,Vector<Palabra> list) {
			int cont=1;
			for(int i =0;i<list.size()&&list.get(pos).count>list.get(i).count;i++) {
				if(i==pos)
					continue;
				if(list.get(pos).s.contains(list.get(i).s)) {
					System.out.println(cont+"- "+list.get(i).s);
					cont++;
				}
			}
		}
		
		//Palindromo?
		public static Boolean isPalindromo(Palabra palabrita) {
			int mitad= palabrita.s.length()/2;
			Palabra a = new Palabra(palabrita.s.substring(0, mitad),"");
			Palabra b =  new Palabra(palabrita.s.substring((palabrita.s.length()%2==0?mitad:mitad+1)),"");
			
			b.count = b.Hashinginv(b.s);
			
			//System.out.println(a.s+" "+b.s);
			//System.out.println(a.count+" "+b.count);
			
			if(a.count==b.count) {
				return true;
			}
			return false;
		}
		public static Boolean searchLongest(Palabra palabrita) {
			Palabra a = new Palabra("" ,"");
			//System.out.println(palabrita.s.length());
			for(int i =palabrita.s.length();i>0;i--) {
				for(int j = 0 ;j+i<=(palabrita.s.length());j++) {
					a = new Palabra(palabrita.s.substring(j,i+j),"");
					
					//System.out.println(a.s+" "+j+" "+i+" "+palabrita.s);
					if(isPalindromo(a)==true) {
						System.out.println(a.s+" es la mayor secuencia que es palindromo");
						return true;
					}
				}
			}
			return false;
		}
		
		
		
		//Buscar posicion de la palabra
		public static int Search(String s,Vector<Palabra> list) {
			for(int i =0;i<list.size();i++) {
				if(s.equalsIgnoreCase(list.get(i).s)) {
					return i;
				}
			}
			System.out.println("Esta palabra no se encuentra en el diccionario.");
			return -1;
		}
}

	
