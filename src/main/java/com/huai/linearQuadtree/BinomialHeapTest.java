package com.huai.linearQuadtree;

/*
* Java Program to Implement Binomial Heap
*/

import java.util.Scanner;

/* Class BinomialHeapNode */
class BinomialHeapNode
{
    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;

    /* Constructor */
    public BinomialHeapNode(int k)
    {
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }
    /* Function reverse */
    public BinomialHeapNode reverse(BinomialHeapNode sibl)
    {
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }
    /* Function to find min node */
    public BinomialHeapNode findMinNode()
    {
        BinomialHeapNode x = this, y = this;
        int min = x.key;

        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }

        return y;
    }
    /* Function to find node with key value */
    public BinomialHeapNode findANodeWithKey(int value)
    {
        BinomialHeapNode temp = this, node = null;

        while (temp != null)
        {
            if (temp.key == value)
            {
                node = temp;
                break;
            }
            if (temp.child == null)
                temp = temp.sibling;
            else
            {
                node = temp.child.findANodeWithKey(value);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }

        return node;
    }
    /* Function to get size */
    public int getSize()
    {
        return (1 + ((child == null) ? 0 : child.getSize()) + ((sibling == null) ? 0 : sibling.getSize()));
    }
}

/* class BinomialHeap */
class BinomialHeap
{
    private BinomialHeapNode Nodes;
    private int size;

    /* Constructor */
    public BinomialHeap()
    {
        Nodes = null;
        size = 0;
    }
    /* Check if heap is empty */
    public boolean isEmpty()
    {
        return Nodes == null;
    }
    /* Function to get size */
    public int getSize()
    {
        return size;
    }
    /* clear heap */
    public void makeEmpty()
    {
        Nodes = null;
        size = 0;
    }
    /* Function to insert */
    public void insert(int value)
    {
        if (value > 0)
        {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null)
            {
                Nodes = temp;
                size = 1;
            }
            else
            {
                unionNodes(temp);
                size++;
            }
        }
    }
    /* Function to unite two binomial heaps */
    private void merge(BinomialHeapNode binHeap)
    {
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;

        while ((temp1 != null) && (temp2 != null))
        {
            if (temp1.degree == temp2.degree)
            {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            }
            else
            {
                if (temp1.degree < temp2.degree)
                {
                    if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree))
                    {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    }
                    else
                    {
                        temp1 = temp1.sibling;
                    }
                }
                else
                {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == Nodes)
                    {
                        Nodes = temp1;
                    }
                    else
                    {

                    }
                }
            }
        }
        if (temp1 == null)
        {
            temp1 = Nodes;
            while (temp1.sibling != null)
            {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        }
        else
        {

        }
    }
    /* Function for union of nodes */
    private void unionNodes(BinomialHeapNode binHeap)
    {
        merge(binHeap);

        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

        while (nextTemp != null)
        {
            if ((temp.degree != nextTemp.degree) || ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree)))
            {
                prevTemp = temp;
                temp = nextTemp;
            }
            else
            {
                if (temp.key <= nextTemp.key)
                {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                }
                else
                {
                    if (prevTemp == null)
                    {
                        Nodes = nextTemp;
                    }
                    else
                    {
                        prevTemp.sibling = nextTemp;
                    }
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }
    /* Function to return minimum key */
    public int findMinimum()
    {
        return Nodes.findMinNode().key;
    }
    /* Function to delete a particular element */
    public void delete(int value)
    {
        if ((Nodes != null) && (Nodes.findANodeWithKey(value) != null))
        {
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }
    /* Function to decrease key with a given value */
    public void decreaseKeyValue(int old_value, int new_value)
    {
        BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
        if (temp == null)
            return;
        temp.key = new_value;
        BinomialHeapNode tempParent = temp.parent;

        while ((tempParent != null) && (temp.key < tempParent.key))
        {
            int z = temp.key;
            temp.key = tempParent.key;
            tempParent.key = z;

            temp = tempParent;
            tempParent = tempParent.parent;
        }
    }

    /* Function to extract the node with the minimum key */
    public int extractMin()
    {
        if (Nodes == null)
            return -1;

        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();

        while (temp.key != minNode.key)
        {
            prevTemp = temp;
            temp = temp.sibling;
        }

        if (prevTemp == null)
        {
            Nodes = temp.sibling;
        }
        else
        {
            prevTemp.sibling = temp.sibling;
        }

        temp = temp.child;
        BinomialHeapNode fakeNode = temp;

        while (temp != null)
        {
            temp.parent = null;
            temp = temp.sibling;
        }

        if ((Nodes == null) && (fakeNode == null))
        {
            size = 0;
        }
        else
        {
            if ((Nodes == null) && (fakeNode != null))
            {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            }
            else
            {
                if ((Nodes != null) && (fakeNode == null))
                {
                    size = Nodes.getSize();
                }
                else
                {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }
        return minNode.key;
    }

    /* Function to display heap */
    public void displayHeap()
    {
        System.out.print("\nHeap : ");
        displayHeap(Nodes);
        System.out.println("\n");
    }
    private void displayHeap(BinomialHeapNode r)
    {
        if (r != null)
        {
            displayHeap(r.child);
            System.out.print(r.key +" ");
            displayHeap(r.sibling);
        }
    }
}


/* Class BinomialHeapTest */
public class BinomialHeapTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Binomial Heap Test\n\n");

        /* Make object of BinomialHeap */
        BinomialHeap bh = new BinomialHeap( );

        char ch;
        /*  Perform BinomialHeap operations  */
        do
        {
            System.out.println("\nBinomialHeap Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete ");
            System.out.println("3. size");
            System.out.println("4. check empty");
            System.out.println("5. clear");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Enter integer element to insert");
                    bh.insert( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter element to delete ");
                    bh.delete( scan.nextInt() );
                    break;
                case 3 :
                    System.out.println("Size = "+ bh.getSize());
                    break;
                case 4 :
                    System.out.println("Empty status = "+ bh.isEmpty());
                    break;
                case 5 :
                    bh.makeEmpty();
                    System.out.println("Heap Cleared\n");
                    break;
                default :
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            /* Display heap */
            bh.displayHeap();

//            System.out.println("\nDo you want to continue (Type y or n) \n");
//            ch = scan.next().charAt(0);
        //} while (ch == 'Y'|| ch == 'y');
        } while (true);
    }
}