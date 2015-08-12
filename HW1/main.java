import java.util.*;
import java.io.*;


public class main
{
	public static void main(String[] args) {
      ArrayList<String> lines = new ArrayList<String>();
      ArrayList<DfsCollection> nodeCollections = new ArrayList<DfsCollection>();
      
		try {
			File file = new File("testData.txt");
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
      
      for (int itterOut = 0; itterOut < lines.size(); itterOut++) {
         DfsCollection nodeCollection = new DfsCollection();
         
         String line = lines.get(itterOut).replaceAll("\\D+","");
                  
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
      
      for(int mainItter = 0; mainItter < nodeCollections.size(); mainItter++){
         DfsCollection nodeCollection = nodeCollections.get(mainItter);
         DfsGraph[] graphsInCollection = nodeCollection.findGraps();
               
         String graphCountLine = "";
         String cycleCountLine = "";
         int totalCycles = 0;
               
         System.out.println("");
         System.out.println("Collection " + (mainItter + 1) + ":");
         
         graphCountLine = graphsInCollection.length + " Connected Tree(s) Found: ";
         
         for (int itterOut = 0; itterOut < graphsInCollection.length; itterOut++) {
            DfsNode[] currentNodeList = graphsInCollection[itterOut].getNodesInGraph();
            totalCycles += graphsInCollection[itterOut].countCycles();
            
            graphCountLine += "{" + currentNodeList[0].getId();
            
            for (int itter = 1; itter < currentNodeList.length; itter++) {
               graphCountLine += ", " + currentNodeList[itter].getId();
            }
            
            ArrayList<DfsNode[]> nodeChains = graphsInCollection[itterOut].listCycles();
   
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
            System.out.println(totalCycles + " cycles found: " + cycleCountLine);
         }
         
         System.out.println("-------------------------------------");
      }
	}
}

