package AbstractGraph;

public class Edge<V, L extends Comparable<L>> implements AbstractEdge<V, L>, Comparable<Edge<V, L>> {

    private V start;
    private V end;
    private L label;

    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    @Override
    public V getStart() {
        return start;
    }

    @Override
    public V getEnd() {
        return end;
    }

    @Override
    public L getLabel() {
        return label;
    }

    @Override
    public int compareTo(Edge<V, L> o) {
        if(o == null){
            return 1;
        }
        return label.compareTo(o.getLabel());
    }

    @Override
    public String toString(){
        return start + "," + end + "," + label;
    }
}
