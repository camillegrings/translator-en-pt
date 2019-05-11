package br.com.unisinos.trabalhogb;

public class List {
	private Node firstNode;
	   private Node lastNode;
	   private String name;  // string like "list" used in printing

	   // construct empty List with "list" as the name
	   public List() { 
	      this( "list" ); 
	   }  

	   // construct an empty List with a name
	   public List( String listName ) {
	      name = listName;
	      firstNode = lastNode = null;
	   }
	   
	  public Node getFirst(){
	       return firstNode;
	    }
	   
	   public Node getLast(){
		   return lastNode;
	   }

	   // insert Object at front of List
	   public  void insertAtFront( Object insertItem ) {
	      if ( isEmpty() ) // firstNode and lastNode refer to same object
	         firstNode = lastNode = new Node( insertItem );

	      else // firstNode refers to new node
	         firstNode = new Node( insertItem, firstNode );
	   }

	   // insert Object at end of List
	   public  void insertAtBack( Object insertItem ) {
	      if ( isEmpty() ) // firstNode and lastNode refer to same Object
	         firstNode = lastNode = new Node( insertItem );

	      else {// lastNode's nextNode refers to new node
	         lastNode.setNext (new Node( insertItem ));
	         lastNode = lastNode.getNext();
	      }
	   }

	   // remove first node from List
	   public  Object removeFromFront() throws EmptyListException {
	      if ( isEmpty() ) // throw exception if List is empty
	         throw new EmptyListException( name );

	      Object removedItem = firstNode.getData(); // retrieve data being removed

	      // update references firstNode and lastNode 
	      if ( firstNode == lastNode )
	         firstNode = lastNode = null;
	      else
	         firstNode = firstNode.getNext();

	      return removedItem; // return removed node data

	   } // end method removeFromFront

	   // remove last node from List
	   public  Object removeFromBack() throws EmptyListException {
	      if ( isEmpty() ) // throw exception if List is empty
	         throw new EmptyListException( name );

	      Object removedItem = lastNode.getData(); // retrieve data being removed

	      // update references firstNode and lastNode
	      if ( firstNode == lastNode )
	         firstNode = lastNode = null;

	      else { // locate new last node
	         Node current = firstNode;

	         // loop while current node does not refer to lastNode
	         while ( current.getNext() != lastNode )
	            current = current.getNext();
	   
	         lastNode = current; // current is new lastNode
	         current.setNext(null);
	      }

	      return removedItem; // return removed node data

	   } // end method removeFromBack

	   // determine whether list is empty
	   public boolean isEmpty() { 
	      return firstNode == null; // return true if List is empty
	   }

	   // output List contents
	   public  void print() {
	      if ( isEmpty() ) {
	         System.out.println( "Empty " + name );
	         return;
	      }

	      System.out.print( "The " + name + " is: " );
	      Node current = firstNode;

	      // while not at end of list, output current node's data
	      while ( current != null ) {
	         System.out.print( current.getData().toString() + " " );
	         current = current.getNext();
	      }

	      System.out.println( "\n" );
	   }
	   
	   public boolean insertAfter(Object obj1, Object obj2){ 
			if ( isEmpty() ) // se a lista estiver vazia 
				return false; 
			boolean retorno = false; 
			Node current = firstNode; 
			while (current!=null) { 
				if (obj1.equals(current.getData())) { 
					Node tmpNode = new Node( obj2 ); 
					if ( current.getNext() != null ) { //se tiver um próximo elemento 
						tmpNode.setNext(current.getNext()); 
				} else { //se for o ultimo elemento 
					lastNode = tmpNode; 
					tmpNode.setNext(null); 
				}  //ou utilizar o método insertAtBack()
				current.setNext(tmpNode); 
				retorno = true; 
				break; 
				} else { 
				current = current.getNext(); 
				} 
			} 
			return retorno; 
		}
	   
	   public boolean remove(Object o) { 
			if (isEmpty()){ //se lista estiver vazia 
				return false; 
			} 
			if (firstNode.getData().equals(o)){ //se for o primeiro 
				removeFromFront(); 
				return true; 
			} else if (lastNode.getData().equals(o)){ //se for o último 
				removeFromBack(); 
				return true;
			} 
			//se for um nodo interno 
			Node tmp = firstNode; //procura elemento 
			Node ant = firstNode; 
			while (tmp != null && !tmp.getData().equals(o)){ 
				ant = tmp; 
				tmp = tmp.getNext(); 
			} 
			if (tmp == null)//se não encontrou 
				return false; 
			else 
				ant.setNext(tmp.getNext()); 
			return true; 
		} 
	  
	   public boolean troca_seg_pen(){
		   Object troca;
		   int c=1;
		   if(isEmpty()) return false;
		   if(firstNode == lastNode) return false;
		   Node seg, pen = firstNode;
		   while (pen.getNext()!= lastNode){
			   c++;
			   pen=pen.getNext();
		   }
		   if(c>=3){
			 seg=firstNode.getNext();
			 troca = seg.getData();
			 seg.setData(pen.getData());
			 pen.setData(troca);
			 return true;
		   }
		   else	   return false;
		   }
	   
	   public boolean remove_maior(){
		   Object dados;
		   Node  antmaior=null, ant = null, maior = firstNode;
		   if(isEmpty()) return false;
		   Node current = firstNode;
		   while (current != null){
			   if((Integer) current.getData() > (Integer) maior.getData()){
				   antmaior = ant;
				   maior = current;
			   }
			   ant=current;
			   current=current.getNext();
		   }
		  if (maior == firstNode) {
			  try { 
			         Object removedObject = removeFromFront();
			         System.out.println( removedObject.toString() + " removed" );
			  }
			  catch ( EmptyListException emptyListException ) {
			         emptyListException.printStackTrace();
			      }
		  } else  if(maior == lastNode) {
			  try { 
			         Object removedObject = removeFromBack();
			         System.out.println( removedObject.toString() + " removed" );
			  }
			  catch ( EmptyListException emptyListException ) {
			         emptyListException.printStackTrace();
			      }
			
		  } else {
			  antmaior.setNext(maior.getNext());
			  System.out.println( maior.getData().toString() + " removed" );
		  }
		  return true;
		   }    
	   
	   public boolean remove_maior_versao2(){
		   Object dados;
		   Node  maior = firstNode;
		   if(isEmpty()) return false;
		   Node current = firstNode;
		   while (current != null){
			   if((Integer) current.getData() > (Integer) maior.getData()){
				   maior = current;
			   }
			   current=current.getNext();
		   }
		   try { 
			         Object removedObject = remove(maior.getData());
			         System.out.println( removedObject.toString() + " removed" );
			  }
			  catch ( EmptyListException emptyListException ) {
			         emptyListException.printStackTrace();
			      }
		  
		  return true;
		   }    
}
