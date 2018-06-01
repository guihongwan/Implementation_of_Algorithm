import java.util.Random;

import com.ghwan.graph.datastructure.BinaryHeap;
import com.ghwan.graph.datastructure.DisjSets;
import com.ghwan.graph.datastructure.MyLinkedList;
import com.ghwan.graph.representation.Edge;
import com.ghwan.graph.representation.Graph;
import com.ghwan.graph.representation.Vertex;

/**
 * 
 * 
 * @author wanguihong
 *
 */
public class MST{
    private static final boolean DEBUG = true;
    
    public static void main(String[] args){
      	Vertex[] G = Graph.createUGraph();
    	
        //DisjSets
        int NumElements = G.length;
        DisjSets ds = new DisjSets( NumElements );
        
        Vertex[] A = Graph.createUGraphWithoutEdge();
        
        //sort the Edges by weight non-decreasingly
        //PS: use this can be done O(N) public BinaryHeap( AnyType [ ] items )
        BinaryHeap<Edge> heap = new BinaryHeap<Edge>();
        for(Vertex u:G) {//2E
        	    MyLinkedList<Edge> adj = u.getAdj();
        	    for(Edge e: adj) {
        	    	    heap.insert(e);
        	    }
        }
        //test the heap
//        while(!heap.isEmpty()) {
//        	    Edge edge = heap.deleteMin();
//        	    System.out.println("<" + edge.u.getName() +","+edge.v.getName()+"> :" + edge.w );
//        }
        
        
        //for verification. total_loop should be V-1, 
        //but it will be greater than V-1, because the graph is represented by adjacent list
        int total_loop = 0;
        while(!checkResult(ds,NumElements) && !heap.isEmpty()){
          	//total_loop++;
            Edge edge = heap.deleteMin();
            Vertex u = edge.u;
            Vertex v = edge.v;
            //System.out.println("<" + u.getName() +","+v.getName()+"> :" + edge.w );
            
            int u_root = ds.find(u.id);
            int v_root = ds.find(v.id);
            
            //System.out.println("<" + u_root +","+v_root+"> " );
            
            if(u_root != v_root ) { //|V|-1
            	    Edge new_edge = new Edge(A[u.id], A[v.id], edge.w);
            	    A[u.id].getAdj().add(new_edge);
            	    ds.union(u_root, v_root);
            	    total_loop++;
            	    //System.out.println("----union:" + u.getName() +","+v.getName());
            	    //printDisjSets(ds, NumElements);
            	    //System.out.println();
            }
            
        }
      	//At the end, all elements in one set
        //we print them out to check again.
        if(DEBUG){
        	    System.out.println("|V| = " + A.length);
        	    System.out.println("# of loop = "+total_loop);
        	    printDisjSets(ds,NumElements);
        }
        
      	Display mst = new Display("MST-KRUSKAL");
      	mst.drawOrignalGraph(G);
        mst.drawMST(A);
    }
    //check whether the result meet the requirement-all elements should be in only one set
    //true: if meet;otherwise return false
    private static boolean checkResult(DisjSets ds, int NumElements){
        int init = ds.find(0);
        for(int i = 1;i<NumElements;i++){
            if(init != ds.find(i)) return false;
        }
        return true;
    }
    private static void printDisjSets(DisjSets ds, int NumElements) {

            for(int i = 0;i<NumElements;i++){
            	    System.out.print(ds.find(i) + "*");
            }
        
    }
}