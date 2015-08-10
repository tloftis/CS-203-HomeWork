import java.util.*;

public class main
{
	public static void main(String[] args) {
      DfsNode DfsNodeA = new DfsNode("a");
      DfsNode DfsNodeB = new DfsNode("b");
      DfsNode DfsNodeC = new DfsNode("c");
      DfsNode DfsNodeD = new DfsNode("d");
      
      
		System.out.println(DfsNodeA.getId());
		System.out.println(DfsNodeB.getId());
		System.out.println(DfsNodeC.getId());
		System.out.println(DfsNodeD.getId());   
      
      System.out.println("Lists");   
      System.out.println("b".compareTo("a")<0);
      System.out.println("a".compareTo("b")<0);
      System.out.println("b".compareTo("b")<0);

      DfsNodeA.connectNode(DfsNodeC);   
      DfsNodeA.connectNode(DfsNodeB);   
      DfsNodeA.connectNode(DfsNodeD);  
      
      DfsNode[] listA = DfsNodeA.getOrderedNodes();
      DfsNode[] listB = DfsNodeB.getOrderedNodes();
      
      for (int itter = 0; itter < listA.length; itter++) {
         System.out.println(listA[itter].getId());
      }
      
      for (int itter = 0; itter < listB.length; itter++) {
         System.out.println(listB[itter].getId());
      }
	}
}

