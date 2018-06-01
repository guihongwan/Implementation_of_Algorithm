package com.ghwan.graph.datastructure;

// LinearProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 * Modified to LinearProbingHashTable by guihong wan 10/30/2017
 */
public class LinearProbingHashTable<AnyType>
{
    /**
     * Construct the hash table.
     */
    public LinearProbingHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public LinearProbingHashTable( int size )
    {
        allocateArray( size );
        doClear( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, do nothing.
     * @param x the item to insert.
     */
    public boolean insert( AnyType x )
    {
        // Insert x as active
        int currentPos = findPos( x );
        
        if( isActive( currentPos ) )
            return false;

        if( array[ currentPos ] == null )
            ++occupied;
        array[ currentPos ] = new HashEntry<>( x, true );
        theSize++;
        
        // Rehash; see Section 5.5
        //Ensure that the hash table is at least twice as large as the number of elements in it.
        if( occupied > array.length / 2 )
            rehash( );
        
        return true;
    }

    /**
     * Expand the hash table.
     */
    private void rehash( )
    {
        HashEntry<AnyType> [ ] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray( 2 * oldArray.length );
        occupied = 0;
        theSize = 0;

        // Copy table over
        for( HashEntry<AnyType> entry : oldArray )
            if( entry != null && entry.isActive )
                insert( entry.element );
   }

    /**
     * Method that performs linear probing resolution.
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos( AnyType x )
    {
        int currentPos = myhash( x );
        if(DEBUG) System.out.println(x +" original Pos: "+currentPos);
        
        //when the cell is occupied by other
        while( array[ currentPos ] != null &&
                !array[ currentPos ].element.equals( x ) )
        {
            currentPos++;  // Compute ith probe 

            if( currentPos >= array.length )
                currentPos -= array.length;
        }
        
        //if(DEBUG) System.out.println(x +" final Pos: "+currentPos);
        return currentPos;
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return true if item removed
     */
    public boolean remove( AnyType x )
    {
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
        {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        }
        else
            return false;
    }
    
    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains( AnyType x )
    {
        int currentPos = findPos( x );
        return isActive( currentPos );
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActive( int currentPos )
    {
        return array[ currentPos ] != null && array[ currentPos ].isActive;
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty( )
    {
        doClear( );
    }

    private void doClear( )
    {
        occupied = 0;
        theSize = 0; //add by ghwan
        for( int i = 0; i < array.length; i++ )
            array[ i ] = null;
    }
    
    private int myhash( AnyType x )
    {
        int hashVal = x.hashCode( );
 
        hashVal %= array.length;
        if( hashVal < 0 )
            hashVal += array.length;

        return hashVal;
    }
    
    private static class HashEntry<AnyType>
    {
        public AnyType  element;   // the element
        public boolean isActive;  // false if marked deleted

        @SuppressWarnings("unused")
		public HashEntry( AnyType e )
        {
            this( e, true );
        }

        public HashEntry( AnyType e, boolean i )
        {
            element  = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType> [ ] array; // The array of elements
    private int occupied;                 // The number of occupied cells
    private int theSize;                  // Current size

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    @SuppressWarnings("unchecked")
	private void allocateArray( int arraySize )
    {
        array = new HashEntry[ nextPrime( arraySize ) ];
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }
    
    //add by ghwan
    public void printHash(){
        for(int i = 0; i < array.length; i++){
            if(array[i] != null)
                System.out.println(i+":"+array[i].element+" "+array[i].isActive);
        }   
    }
    
    private final boolean DEBUG = false;
    // Simple main
    public static void main( String [ ] args )
    {
        //test LinearProbingHashTable
        //test1:
        System.out.println("**************test1");
        LinearProbingHashTable<Integer> IH = new LinearProbingHashTable<>( 10 );
        IH.insert(20);
        IH.insert(19);
        IH.insert(31);
        IH.insert(30);
        IH.insert(42);
        
        /*the result should be
          0:30
          1:42
          8:19
          9:20
          10:31
        */
        IH.printHash();
        System.out.println();
        
        IH.remove(42);
        IH.printHash();
        System.out.println();
        
        System.out.println("**************test2");
        LinearProbingHashTable<String> H = new LinearProbingHashTable<>( );
        
        long startTime = System.currentTimeMillis( );
        
        final int NUMS = 7;
        final int GAP  =   3;

        System.out.println("***insert***");
    
        String str = "inserted, part1:";
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
            str += i;
            H.insert( ""+i );
        }
        str += " part2:";
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ){
            str += i;
            if( H.insert( ""+i ) )
                System.out.println( "OOPS!!! " + i );//the same item will not be inserted.
        }
        System.out.println(str);
        H.printHash();
        
        
        System.out.println();
        System.out.println("***contains***");
        
        for( int i = 2; i < NUMS; i+=2 ){
            if( !H.contains( ""+i ) )
                System.out.println( "Find fails " + i );
            System.out.println( "Succeed to find " + i );
        }
        
        System.out.println("***remove***");
        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( ""+i );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( H.contains( ""+i ) )
                System.out.println( "OOPS!!! " +  i  );
            System.out.println( "Succeed to not find " + i );
        }
        
        System.out.println();
        
        long endTime = System.currentTimeMillis( );
        System.out.println( "Elapsed time: " + (endTime - startTime) );
    }

}
