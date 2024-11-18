package TADQueue;

class QueueNode <T> {
    private T info;
    private QueueNode<T> prox;
    QueueNode (T value) {
        this.info = value;
    }
    void setInfo (T value) {
        this.info = value;
    }
    T getInfo() {
        return this.info;
    }
    void setProx (QueueNode<T> novoProx) {
        this.prox = novoProx;
    }
    QueueNode<T> getProx() {
        return this.prox;
    }
}