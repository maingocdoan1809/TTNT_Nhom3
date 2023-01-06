package nhom3.huce.ttnt_nhom3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class IDS {

    private int maxDepth;
    /**
     * Lưu đồ thị theo dạng mảng, nhưng đây là mảng đặc biệt dùng... HashMap.
     * Với mỗi một {@code node} trong đồ thị ta sẽ tìm được {@code node} đó có
     * thể đi đến được những {@code node} nào. Đường đi là một chiều
     */
    private HashMap<String, HashSet<String>> nodes;
    private String errorPath = "Cannot find any path.";
    private String path = errorPath;
    private ArrayDeque<Pair> queue;
    private HashSet<String> visited;
    private ArrayList<ArrayList<String>> table;

    /**
     * Lưu tên {@code node} và độ sâu của nó
     * <li>{@code first} : tên</li>
     * <li>{@code second} : độ sâu</li>
     */
    private static record Pair(String first, int second) {

    }

    /**
     * Tạo một cấu trúc cây với {@code maxDepth} cho trước.
     *
     * @param maxDepth: độ sâu tối đa mà thuật toán tìm kiếm sâu dần sẽ tìm.
     */
    public IDS(int maxDepth) {
        this.maxDepth = maxDepth;
        nodes = new HashMap<>();
        this.table = new ArrayList<>();
    }
    public IDS() {
        this(0);
    }

    /**
     * *
     *
     * @param maxDepth : set {@code maxDepth} mới nếu như {@code maxDepth} cũ
     * không cho ra kết quả.
     */
    public void setMaxDepth(int maxDepth) {
        this.table = new ArrayList<>();
        this.maxDepth = maxDepth;
    }

    /**
     * Thêm một {@code node} con cho {@code rootName}, một chiều từ
     * {@code rootName} -> {@code childName}
     *
     * @param rootName : tên {@code node} gốc
     * @param childName : tên {@code node} con của node {@code rootName}
     *
     */
    public void addNode(String rootName, String childName) {
        if (!nodes.containsKey(childName)) {
            nodes.put(childName, new HashSet<>());
        }
        if (!nodes.containsKey(rootName)) {
            nodes.put(rootName, new HashSet<>());
        }
        nodes.get(rootName).add(childName);
    }

    /**
     * @see {@link #addNode(String, String)}
     * @param rootName : tên {@code node} gốc
     * @param childName : tên {@code node} con của node {@code rootName}
     * @param directed : nếu directed = true, đường nối giữa {@code rootName}
     * {@code childName} sẽ là 2 chiều
     */
    public void addNode(String rootName, String childName,
            boolean directed) {
        if (directed) {
            // thêm 2 chiều
            addNode(rootName, childName);
            addNode(childName, rootName);
        } else {
            addNode(rootName, childName);
        }
    }

    /**
     *
     * @return bảng trạng thái các bước duyệt mảng (theo giao diện mà làm)
     */
    public ArrayList<ArrayList<String>> getTable() {
        return this.table;
    }

    /**
     *
     * @return : trả về danh sách các {@code node} trong đồ thị
     */
    public HashMap<String, HashSet<String>> get() {
        // tên của các nút trong đỉnh = 
        
        return this.nodes;
    }

    /**
     *
     * @param start : {@code node} bắt đầu
     * @param dest : {@code node} kết thúc
     * @return đường đi từ start -> dest
     */
    public String travel(String start, String dest) {
        this.path = errorPath;
        this.queue = new ArrayDeque<>();
        this.visited = new HashSet<>();
        queue.addFirst(new Pair(start, 0));
        travelHelper(dest, " ", queue, visited);
        return this.path;
    }

    /**
     *
     * @param dest
     * @param path
     * @param queue
     * @param visited
     */
    private void travelHelper(String dest, String path,
            ArrayDeque<Pair> queue,
            HashSet<String> visited) {

        Pair startNode = queue.pollFirst();
        String start = startNode.first;
        int currDepth = startNode.second;
        ArrayList<String> step = new ArrayList<>();
        //
        step.add(start);
        step.add(currDepth + "");
        //
        visited.add(start);

        if ((currDepth - 1) == this.maxDepth) {
            this.path = errorPath;
            return;
        }
        var children = this.nodes.get(start);
        String childrenString = "";
        for (var child : children) {
            childrenString += child + ", ";
            if (!visited.contains(child)) {
                var childPair = new Pair(child, currDepth + 1);
                if ( !queue.contains(childPair) ) {
                    queue.addLast(childPair);
                }
                
            }
        }

        /// lấy dữ liệu cho table
        if (childrenString.length() >= 2) {
            childrenString = childrenString.substring(0, childrenString.length() - 2);
        }
        step.add(childrenString);
        String openString = "";
        for (var node : queue) {
            openString += node.first + ", ";
        }
        if (openString.length() > 2) {
            openString = openString.substring(0, openString.length() - 2);
        }
        step.add(openString);
        String closeString = "";
        for (var node : visited) {
            closeString += node + ", ";
        }
        if (closeString.length() >= 2) {
            closeString = closeString.substring(0, closeString.length() - 2);
        }
        step.add(closeString);

        // ----------------------/
        if (dest.equals(start)) {
            this.table.add(new ArrayList<>(List.of(dest, currDepth + "",
                    childrenString, openString, closeString)));
            this.path = path;
            return;
        }
        if (queue.size() != 0) {
            this.table.add(step);
            travelHelper(dest, start + " -> " + path, queue, visited);
        }
    }
    // tim kiem sau de tim duong di:
    public static void main(String[] args) {
        IDS tree = new IDS(2);
        tree.addNode("A", "B", true);
        tree.addNode("A", "G");
        tree.addNode("G", "C");
        tree.addNode("C", "B", true);
        tree.addNode("C", "D");

        System.out.println(tree.travel("C", "G"));

        var table = tree.getTable();
        for (var row : table) {
            System.out.print("Start: " + row.get(0));
            System.out.print(" Depth: " + row.get(1));
            System.out.print(" Child: " + row.get(2));
            System.out.print(" Open: " + row.get(3));
            System.out.print(" Close: " + row.get(4));
            System.out.println();
        }
        
        System.out.println("Ten cac dinh");
        var nodeName = tree.get().keySet();
        for (String node : nodeName) {
            System.out.println(node);
        }
    }
}
