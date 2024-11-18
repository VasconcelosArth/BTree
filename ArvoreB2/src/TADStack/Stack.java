package TADStack;

public class Stack <T> {
    private StackNode<T> topo;
    
    public boolean isEmpty() {
        if (this.topo == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isFull() {
        return false;
    }
    
    public T top () {
        return  this.topo.getInfo();
    }
    
    public void push (T value) {
        StackNode<T> novo = new StackNode<T> (value);
        novo.setProx(this.topo);
        this.topo = novo;
    }
    
    public T pop () {
        StackNode<T> aux;
        aux = this.topo;
        this.topo = this.topo.getProx();
        return aux.getInfo();
    }
}
