import java.util.Arrays;

public class ArrayDeque<T>{
    //public T[] a = (T[]) new Object[1000];
    public T[] arr;
    public int size;

    public ArrayDeque(){
        T[] arr = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int flag){
        if((flag == 0)||(size <= 0.25*arr.length && arr.length >= 16)){
            T[] newarr = (T[]) new Object[size*2];
            System.arraycopy(newarr,0,arr,0,size);
            arr = newarr;
            return;
        }


    }
    public void addFirst(T item){
        if(size == arr.length){
            resize(0);
        }

        T temp = arr[0];
        arr[size] = temp;
        arr[0] = item;
        size += 1;

    }

    public void addLast(T item){
        if(size == arr.length){
            resize(0);
        }
        arr[size] = item;
        size += 1;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i = 0; i<size; i++){
            System.out.print(arr[i]);
            System.out.print(' ');
        }
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T item = arr[0];
        System.arraycopy(arr,0,arr,1,size - 1);
        //arr = Arrays.copyOfRange(arr,1,size-1);
        arr[size - 1] = null;
        size -= 1;
        resize(1);
        return item;

    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        T item = arr[size - 1];
        arr[size - 1] = null;
        size -= 1;
        resize(1);
        return item;

    }

    public T get(int index){
        if(index >=size){
            return null;
        }
        return arr[index];
    }

}