import java.util.List;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DependencyGraph {
    private HashMap<Integer, HashSet<Integer>> adjMatrix;
    private int[] colors;
    private boolean error = false;
    private int nColors;
    private List<BitSet> in;
    private List<BitSet> out;
    private List<BitSet> def;
    private int localsCount;
    private int precolorCount;
    private boolean debugMode = false;

    public DependencyGraph(List<BitSet> def, List<BitSet> in, List<BitSet> out, int localsCount, int precolorCount) {

        this.in = in;
        this.out = out;
        this.def = def;
        this.localsCount = localsCount;
        this.precolorCount = precolorCount;
        this.reset();

        if(this.debugMode)
            this.displayDependencies();
    }

    private void displayDependencies(){

        // Display dependencies
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjMatrix.entrySet()) {
            System.out.println("Node: " + entry.getKey());

            StringBuilder builder = new StringBuilder("Adj: ");
            for (Integer coiso : entry.getValue()) {
                builder.append(coiso + " ");
            }
            System.out.println(builder.toString());
            System.out.println();
        }
    }

    public boolean isError(){

        return this.error;
    }

    public int getUsedColors(){
        return this.nColors;
    }

    private void reset() {

        // Create the adjacency table and node colors array
        adjMatrix = new HashMap<>();
        for (int i = 0; i < localsCount; i++)
            adjMatrix.put(i, new HashSet<>());

        colors = new int[localsCount];
        Arrays.fill(colors, -1);

        for(int i = 0; i < this.precolorCount; i++){
            this.colors[i] = i;
        }

        // Add node dependencies
        for (int i = 0; i < in.size(); i++) {
            BitSet inNode = in.get(i);
            BitSet outNode = out.get(i);
            BitSet defNode = def.get(i);

            // Simultaneously in "in"
            this.addAdjacencyFrom(inNode);

            // Simultaneously in "out"
            this.addAdjacencyFrom(outNode);

            // Simultaneously in "out" and "in" or in "def"
            BitSet last = new BitSet(this.localsCount);
            last.or(inNode);
            last.and(outNode);
            last.or(defNode);

            this.addAdjacencyFrom(last);
        }
    }

    private void addAdjacencyFrom(BitSet bs){

        for (int j = bs.nextSetBit(0); (j >= 0) && (j < localsCount); j = bs.nextSetBit(j + 1)) {
            for (int k = bs.nextSetBit(j + 1); (k >= 0) && (k < localsCount); k = bs.nextSetBit(k + 1)) {
                addAdjacency(j, k);
            }
        }
    }

    private void addAdjacency(int x, int y) {
        if (x < y) {
            adjMatrix.get(x).add(y);
        } else {
            adjMatrix.get(y).add(x);
        }
    }

    private boolean areAdjacent(int x, int y) {
        if (x < y) {
            return adjMatrix.get(x).contains(y);
        } else {
            return adjMatrix.get(y).contains(x);
        }
    }

    private int getNodeDegree(int x) {

        // Number of connections in own hashset
        int total = (adjMatrix.get(x) == null) ? 0 : adjMatrix.get(x).size();

        // Number of appearences in other hashset
        for (int i = 0; i < this.colors.length; i++) {
            HashSet<Integer> adjVector = adjMatrix.get(i);
            if (adjVector != null && adjVector.contains(x))
                total++;
        }
        return total;
    }

    private HashSet<Integer> getNodeAdjacencies(int x) {
        HashSet<Integer> adjs = (adjMatrix.get(x) == null) ? new HashSet<>()
                : (HashSet<Integer>) adjMatrix.get(x).clone();
        for (int i = 0; i < this.colors.length; i++) {
            HashSet<Integer> adjVector = adjMatrix.get(i);
            if (adjVector != null && adjVector.contains(x))
                adjs.add(i);
        }
        return adjs;
    }

    private int getNodeWithMaxAdjs() {
        int node = -1;
        int maxAdjs = -1;
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjMatrix.entrySet()) {
            int nodeAdjs = getNodeDegree(entry.getKey());
            if (nodeAdjs > maxAdjs) {
                node = entry.getKey();
                maxAdjs = nodeAdjs;
            }
        }

        return node;
    }

    private void cutNodeOffGraph(int x) {
        adjMatrix.remove(x);
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjMatrix.entrySet()) {
            entry.getValue().remove(x);
        }
    }


    private Stack<Integer> getLocalsStack(int maxDegree){
        Stack<Integer> stack = new Stack<>();

        boolean cutNode = false;
        do {

            cutNode = false;
            int node = -1;

            // Check if any node can be cut (cutNode = true)
            for (Map.Entry<Integer, HashSet<Integer>> entry : adjMatrix.entrySet()) {
                node = entry.getKey();

                if (getNodeDegree(node) < maxDegree) {

                    cutNode = true;
                    break;
                }
            }

            // Cut the node and push it
            if (cutNode && (node > -1)) {

                cutNodeOffGraph(node);
                stack.push(node);
            }

            // There still are nodes to be cut and no node was cut -> may-spill
            if (!cutNode && !adjMatrix.isEmpty()) {
                int nodeToSpill = getNodeWithMaxAdjs();
                cutNode = true;
                cutNodeOffGraph(nodeToSpill);
                stack.push(nodeToSpill);
            }

        } while (cutNode);

        return stack;
    }


    private boolean colorGraph(Stack<Integer> stack, int numColors){

        while (!stack.empty()) {

            // Color one more node
            int nodeToColor = stack.pop();
            HashSet<Integer> nodeAdjs = getNodeAdjacencies(nodeToColor);
            int color = 0;
            
            if(this.colors[nodeToColor] != -1)
                continue;
            
            // Try another color

            boolean canPass;
            do {

                canPass = true;
                for (int adjNode : nodeAdjs) {
                    int adjColor = colors[adjNode];

                    // Color exist in adjacent nodes
                    if (adjColor == color) {
                        color++;
                        canPass = false;
                    }

                    // Ran out of colors
                    if (color >= numColors) {
                        this.error = true;
                        this.reset();
                        return false;
                    }

                }
            } while (!canPass);

            // Color node
            colors[nodeToColor] = color;
        }

        if ((1 + Arrays.stream(this.colors).max().getAsInt()) > numColors) {
            this.error = true;
            this.reset();
            return false;
        }

        return true;
    }

    public int[] colorizeGraph(int numColors) {
        this.nColors = numColors;

        // Clone the adjacency matrix
        HashMap<Integer, HashSet<Integer>> adjClone = new HashMap<>();
        for (Map.Entry<Integer, HashSet<Integer>> entry : adjMatrix.entrySet()) {
            adjClone.put(entry.getKey(), (HashSet<Integer>) entry.getValue().clone());
        }

        Stack<Integer> stack = this.getLocalsStack(numColors);
        adjMatrix = adjClone;

        return this.colorGraph(stack, numColors) ? colors : this.colorizeGraph(numColors + 1);
    }
}