import java.util.*;

public class Apriori_1411099 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		/*String[][] data = new String[][]{
 			{ "100", "1,2,5" },
 			{ "101", "2,4" },
 			{ "102", "2,3"  },
 			{ "103", "1,2,4" },
 			{ "104", "1,3" },
 			{ "105", "2,3" },
 			{ "106", "1,3" },
 			{ "107", "1,2,3,5" },
 			{ "108", "1,2,3" }};*/ 
 			
 	String[][] data = new String[][]{
 			{ "100", "1,3,4" },
 			{ "101", "2,3,5" },
 			{ "102", "1,2,3,5"  },
 			{ "103", "2,5" }}; 
 				System.out.println("The Final Item Set is: ");
 			
 		Map<String, Integer> count = new TreeMap<>();
 		
 		for(int i = 0; i < data.length; i++){
 			String ip = data[i][1];
 			StringTokenizer st = new StringTokenizer(ip,",",false);
 			while(st.hasMoreTokens()){
 				String x = st.nextElement().toString();
 				Integer c = count.get(x);
 				if(c != null){
 					c++;
 					count.put(x,c);
 					} else{ 							
 					count.put(x,1);
 					} 				
 				}
 			}
		
		ArrayList<String> elements = new ArrayList<>();
		for(String name : count.keySet()){ 
			if(count.get(name) >= 2)
				elements.add(name);
				
		//	else
		//		count.remove(name);				
			}
		
		// Clustering two items sets together to find count in data set.
		ArrayList<ArrayList<Integer>> setTwo = new ArrayList<>();
		for(String i : elements){
			int fst = Integer.parseInt(i);
			for(String j : elements){
				int sec = Integer.parseInt(j);
				if(fst < sec){
					ArrayList<Integer> ele = new ArrayList<Integer>();
					ele.add(0,fst);
					ele.add(1,sec);
					setTwo.add(ele);
					}
				}
			}
			
		//Searching the occurence of each pair in the data.
		ArrayList<ArrayList<Integer>> mainData = new ArrayList<>();
		count.clear();	
		for(ArrayList<Integer> ele : setTwo){
			int f = ele.get(0);
			int s = ele.get(1);			
			String key = f + "," + s;
			mainData.clear();
			
		   for(int i = 0; i < data.length; i++){
			ArrayList<Integer> n = new ArrayList<>();
 			String ip = data[i][1];
 			StringTokenizer st = new StringTokenizer(ip,",",false);
 			while(st.hasMoreTokens()){
 				int x = Integer.parseInt(st.nextElement().toString());
 				n.add(x);
 				}
 			
 			if(n.contains(f) && n.contains(s)){
 				Integer c = count.get(key);
 				if(c != null){
 					c++;
 					count.put(key,c);
 					} else{ 						
 					count.put(key,1);
 					}
 			}  		
 			mainData.add(n);				
			}														
		}
		
	//	System.out.println(mainData);

		elements.clear();
		for(String key : count.keySet()){
				int val = count.get(key);
				if(!(val < 2))
					elements.add(key);
		}

		
		//Adding the two sets from string to integers.
		setTwo.clear();
		for(String e : elements){
			StringTokenizer st = new StringTokenizer(e,",",false);
			ArrayList<Integer> ele = new ArrayList<>();
			ele.add(0,Integer.parseInt(st.nextToken()));
			ele.add(1,Integer.parseInt(st.nextToken()));			
			setTwo.add(ele);
			}
			
	//	System.out.println(setTwo);		
		int nItems = setTwo.size();
		
		// Creating data sets of three items
		ArrayList<String> dataSet = new ArrayList<>();
		for(int i = 0; i < nItems - 1; i++){		
			for(int j = i + 1; j < nItems; j++){
				ArrayList<Integer> fst = setTwo.get(i);
				ArrayList<Integer> snd = setTwo.get(j);
				
				int ft = fst.get(0);
				int sd = fst.get(1);
				int td = snd.get(0);
				int fr = snd.get(1);
				
				String nextEle = ""; 
				if(ft == td && elements.contains(sd + "," + fr)){
					nextEle = ft + "," + sd + "," + fr;
				} else if(sd == td && elements.contains(ft + "," + fr)){
					nextEle = ft + "," + sd + "," + fr;
				} else if(sd == fr && elements.contains(ft + "," + td)){
					nextEle = ft + "," + td + "," + sd;
				}
				
				if(!dataSet.contains(nextEle) && !nextEle.isEmpty()){
					dataSet.add(nextEle);
				}
			}				
		}
		
	//	System.out.println(dataSet);
	//	count.clear();
			
		//Calculating count for each dataset
		for(String dataEle : dataSet){
			count.clear();
			String key = dataEle;
			StringTokenizer st = new StringTokenizer(dataEle, ",", false);
			int f = Integer.parseInt(st.nextElement().toString());
			int s = Integer.parseInt(st.nextElement().toString());
			int t = Integer.parseInt(st.nextElement().toString());
		//	System.out.println(f + " " + s + " " + t);
						
			for(ArrayList<Integer> chck : mainData){
				if(chck.contains(f) && chck.contains(s) && chck.contains(t)){
					Integer cnt = count.get(key);
					if(cnt == null){
						count.put(key,1);
					} else{
						cnt++;
						count.put(key,cnt);
					}
				}
			}
			
			//System.out.println("The support is: 2");
			
			//Final set and count
			for(String keys : count.keySet()){
				System.out.println(keys + " -> " + count.get(keys));
			}			
		}									
	}				
}
