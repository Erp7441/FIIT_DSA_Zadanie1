package sk.stuba.fiit.martin.szabo.structure;

import sk.stuba.fiit.martin.szabo.utils.Position;
import java.util.ArrayList;

public class Node{
    private Object value = null;
    private Node left = null;
    private Node right = null;
    private Node top = null;

    public Node(){}
    public Node(Object value){
        this.value = value;
    }
    public Node(Node left, Node right, Object value){
        if(left != null){
            left.setTop(this);
        }

        if(right != null){
            right.setTop(this);
        }

        this.left = left;
        this.right = right;
        this.value = value;
    }
    public Node(Node node, boolean left, Object value){
        if(left){
            this.left = node;
        }
        else{
            this.right = node;
        }
        node.setTop(this);
        this.value = value;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public Object getValue(){
        return this.value;
    }
    public Node getTop(){
        return this.top;
    }

    public void setTop(Node node){
        this.top = node;
    }
    public void setLeft(Node node){
        if(node!= null){
            node.setTop(this);
        }
        this.left = node;
    }
    public void setRight(Node node){
        if(node!= null){
            node.setTop(this);
        }
        this.right = node;
    }
    public void setValue(Object value){
        this.value = value;
    }

    /**
     * Prints all nodes in the tree in format: <br><br>
     *
     * [CurrentNode]: [LeftNode], [RightNode]
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.value).append(": ");
        if(this.left!= null){
            sb.append(this.left.getValue()).append(", ");
        }
        else{
            sb.append("(null), ");
        }
        if(this.right!= null){
            sb.append(this.right.getValue());
        }
        else{
            sb.append("(null)");
        }
        return sb.toString();
    }

    /**
     * Gets adjecent node of this node
     * @return adjecent node of this node
     */
    public ArrayList<Node> getAdjects(){
        ArrayList<Node> adjects = new ArrayList<>();
        if(this.left!= null){
            adjects.add(this.left);
        }
        if(this.right!= null){
            adjects.add(this.right);
        }
        return adjects;
    }

    /**
     * Adds adjecent node to this node
     * @param value value of new adjecent node
     */
    public void addAdjecent(Object value){
        if(this.left == null){
            this.left = new Node(value);
            this.left.setTop(this);
        }
        else if(this.right == null){
            this.right = new Node(value);
            this.right.setTop(this);
        }
        else{
            System.err.println(
                    "Could not add node" + value.toString() +
                    " this node is full."
            );
        }
    }

    /**
     * Adds adjecent node to this node
     * @param value value of new adjecent node
     * @param position position of new adjecent node
     */
    public void addAdjecent(Object value, Position position){
        if(position == Position.LEFT){
            if(this.left == null){
                this.left = new Node(value);
                this.left.setTop(this);
                return;
            }
        }
        else if(position == Position.RIGHT){
            if(this.right == null){
                this.right = new Node(value);
                this.right.setTop(this);
                return;
            }
        }

        // TODO refactor this bit to note use "desiredNode" and other implementations of "addAdjecent"
        // If desired position is not found we add it to the other position
        Node desiredNode;
        if(position == Position.LEFT){
            desiredNode = this.left;
        }
        else{
            desiredNode = this.right;
        }

        System.err.println(
                "Could not add node" + value.toString() +
                " to node " + desiredNode.toString() +
                " this node is already taken."
        );
        this.addAdjecent(value);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Node)){
            return false;
        }
        Node node = (Node) obj;
        if(this.value == null){
            if(node.value!= null){
                return false;
            }
        }
        else if(!this.value.equals(node.value)){
            return false;
        }
        if(this.left == null){
            if(node.left!= null){
                return false;
            }
        }
        else if(!this.left.equals(node.left)){
            return false;
        }
        if(this.right == null){
            if(node.right!= null){
                return false;
            }
        }
        else if(!this.right.equals(node.right)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return this.value.hashCode();
    }

}
