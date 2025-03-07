//Make sure that the EnhancedQueue class inherits from Queue.
//        Declare a private instance variable: int m_number which is used in one of the methods you are required to implement.
//        Create a constructor that takes two parameters int cap and int number.
//        The constructor should call the constructor of the super class and initialize the instance variable properly.
//        Create an int method countOdds() that returns the number of the elements that are odd.
//        For example, if a queue contains 5 elements: 10, 6, 9, 12, 7 , then countOdds() returns 2 because 9 and 7 are odd numbers.
//        Override the longerThan method so it returns true if the current queue is longer (i.e., has more elements) than the queue being compared
//        AND the current queue contains exactly m_number of elements. Otherwise, the method returns false. Note: Don't forget to add the @Override keyword!


public class IntNode
{
    private int m_data;
    private IntNode m_link;

    public IntNode(int data, IntNode link) {
        m_data = data;
        m_link = link;
    }
    public int getInfo() {return m_data;}
    public IntNode getLink(){return m_link;}

    public int countDecrements(IntNode myList)
    {
        // type your implementation in the text box below
        // You will get a zero if the method is implemented in a recursive way.
        if (myList == null || myList.getlink() == null) {
            return 0;
        }
        int count = 0;
        IntNode current = myList;

        while (current.getLink() != null) {
            if (current.getInfo() < current.getLink().getInfo()) {
                count++;
            }
            current = current.getLink();
        }
        return count;
    }
}










