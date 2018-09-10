public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
    private E[] elems; // Used to store the elements of this ArrayStack
    private int top;
    @SuppressWarnings( "unchecked" )


   // Constructor
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
        elems = (E[]) new Object[ capacity ];
        top = 0;

    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
        return top == 0;

    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
        elems[ top ] = elem;
        top++;
        if(top==elems.length){
            E[] temp;
            temp=(E[]) new Object[elems.length];
            int capacity=elems.length*2;
            for(int i=0;i<temp.length;i++){
                temp[i]=elems[i];
            }
            elems = (E[]) new Object[ capacity ];
            for(int i=0;i<temp.length;i++){
                elems[i]=temp[i];
            }
        }

    }
    public E pop() {
        
    // ADD YOU CODE HERE
        E saved;

        top--;
        saved = elems[ top ];
        elems[ top ] = null;

        return saved;

    }

    public E peek() {
        
    // ADD YOU CODE HERE
        return elems[ top-1 ];

    }
}
