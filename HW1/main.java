/***************************************************************/
/* Timothy Loftis                                              */
/* Login ID: loft3285                                          */
/* CS-203, Summer 2015                                         */
/* Programming Assignment 1                                    */
/* main class: Uses Dfs Classes to find pares the text of a    */
/*             Data file and display graph information found   */
/*             inside of it                                    */
/***************************************************************/

import java.util.*;//used for mostly ArrayLists
import java.io.*;//used to read files

public class main
{
   /***************************************************************/
   /* Method: main                                                */
   /* Purpose: Uses the text parser and Dfs Classes to generate   */
   /*          Information about the graph formed                 */
   /* Parameters:                                                 */
   /* String[] args: Given by terminal, location of data txt file */
   /* Returns: Void: Output to the console only                   */
   /***************************************************************/
	public static void main(String[] args) {
      if(args.length == 0){
         System.out.println("No data file specified");
         return;
      }
      
      //get lines of the text file then parse data from text file into collections
      ArrayList<String> lines = getTextByLine(args[0]);
      ArrayList<DfsCollection> nodeCollections = textParser(lines);
      
      //Loop through each collection found and output the data
      for(int mainItter = 0; mainItter < nodeCollections.size(); mainItter++){
         DfsCollection nodeCollection = nodeCollections.get(mainItter);//current focus collection
         DfsGraph[] graphsInCollection = nodeCollection.findGraps();//Holds all graphs in collection
               
         String graphCountLine = "";//holds output string for all data about graphs
         String cycleCountLine = "";//holds output string for all data about cycles

         int totalCycles = 0;//will hold total count of cycles in the collection
               
         System.out.println("\nCollection " + (mainItter + 1) + ":");
         
         graphCountLine = graphsInCollection.length + " Connected Tree(s) Found: ";
         
         //go through each graph in the collection to ouput that graph data line
         for (int itterOut = 0; itterOut < graphsInCollection.length; itterOut++) {
            DfsNode[] currentNodeList = graphsInCollection[itterOut].getNodesInGraph();
            //counts up the cycles of all graphs collective cycles
            totalCycles += graphsInCollection[itterOut].countCycles(); 
            
            //builds the data displayed to the user about content of graph
            graphCountLine += "{" + currentNodeList[0].getId();
            for (int itter = 1; itter < currentNodeList.length; itter++) {
               graphCountLine += ", " + currentNodeList[itter].getId();
            }
            
            //holds all cycles in current graph
            ArrayList<DfsNode[]> nodeChains = graphsInCollection[itterOut].listCycles();
            
            //go through each cycle in the graph to generate easly read data
            for (int itter = 0; itter < nodeChains.size(); itter++){
               DfsNode[] tempNodes = nodeChains.get(itter);
               cycleCountLine += "(" + tempNodes[0].getId();
               
               for (int itterInner = 1; itterInner < tempNodes.length; itterInner++){
                  DfsNode tempNode = tempNodes[itterInner];
                  cycleCountLine += "-" + tempNode.getId();
               }
               cycleCountLine += "-" + tempNodes[0].getId();
               
               cycleCountLine += ") ";
            }
            
            graphCountLine += "} ";
         }
         
         System.out.println(graphCountLine);
         
         if(totalCycles == 0){
            System.out.println("The collection is acyclic.");
         }else{
            System.out.println(totalCycles + " cycle(s) found: " + cycleCountLine);
         }
      }
	}
   
   /***************************************************************/
   /* Method: textParser                                          */
   /* Purpose: Parses out information line by line formatted      */
   /*          In a particular manner and makes DfsCollections    */
   /* Parameters:                                                 */
   /* ArrayList<String> lines: List of each line from a data text */
   /*                          file                               */
   /* Returns: ArrayList<DfsCollection>: DfsCollections made from */
   /*                   the data found in the lines of data given */
   /***************************************************************/
   public static ArrayList<DfsCollection> textParser(ArrayList<String> lines){
      ArrayList<DfsCollection> nodeCollections = new ArrayList<DfsCollection>();
      
      //Go through line by line of the text file and process the found data
      for (int itterOut = 0; itterOut < lines.size(); itterOut++) {
         DfsCollection nodeCollection = new DfsCollection();
         
         String line = lines.get(itterOut).replaceAll("\\D+","");
         
         int nodeCount = Integer.parseInt(Character.toString(line.charAt(0)));
         
         //Create the nodes specified by the first number in the text file
         for (int itter = 1; itter <= nodeCount; itter++) {
            nodeCollection.addNewNode("" + itter);
         }
         
         //Go through the line and connect the nodes as listed
         for (int itter = 1; itter < line.length(); itter++) {
            DfsNode node1;
            DfsNode node2;
            String nodeId = Character.toString(line.charAt(itter));
            
            if(nodeCollection.nodeExists(nodeId)){
               node1 = nodeCollection.getNodeById(nodeId);
            }else{
               node1 = nodeCollection.addNewNode(nodeId);
            }
            
            itter++;
            nodeId = Character.toString(line.charAt(itter));

            if(nodeCollection.nodeExists(nodeId)){
               node2 = nodeCollection.getNodeById(nodeId);
            }else{
               node2 = nodeCollection.addNewNode(nodeId);
            }
            
            nodeCollection.connectNodes(node1, node2);
         }
         
         nodeCollections.add(nodeCollection);
      }
      
      return nodeCollections;
   }
   
   /***************************************************************/
   /* Method: getTextByLine                                       */
   /* Purpose: Get all information from a text file line by line  */
   /* Parameters:                                                 */
   /* String fileLocation: location of text file                  */
   /* Returns: ArrayList<String>: array list of each line of text */
   /*                               in the given file             */
   /***************************************************************/
   public static  ArrayList<String> getTextByLine(String fileLocation){
      ArrayList<String> lines = new ArrayList<String>();
      
		try {
			File file = new File(fileLocation);
			FileReader fileReader = new FileReader(file);
         
			BufferedReader bufferedReader = new BufferedReader(fileReader);
         
			String line;
			while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
			}
         
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
      
      //lines have to have an odd number of chars or it is invalid
      for (int itterOut = 0; itterOut < lines.size(); itterOut++) {
         if(lines.get(itterOut).replaceAll("\\D+","").length()%2 != 1){
            lines.remove(itterOut);
            itterOut--;
         }
      }
      
      return lines;
   }
}

