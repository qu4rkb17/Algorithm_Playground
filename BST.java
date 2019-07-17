//Binary Search Tree
public class BST<Key extends Comparable<Key>, Value>{
	private Node root;

	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;

		public Node(Key key, Value val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public int size(){
		return size(root);
	}

	private int size(Node x){
		if (x == null) return 0;
		else return x.N;
	}

//get(), put()
	public Value get(Key key){
		return get(root, key);
	}

	private Value get(Node x, Key key){
		if (x == null)	return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)	return get(x.left, key);
		else if (cmp > 0)	return get(x.right, key);
		else	return x.val;
	}

	public void put(Key key, Value val){
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val){
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)	x.left = put(x.left, key, val);
		else if (cmp > 0)	x.right = put(x.right, key, val);
		else	x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

//max(), min(), floor(), ceiling()
	public Value max(){
		return man(root).key;
	}

	private Node max(Node x){
		if (x.right == null) return x;
		return max(x.right);
	}

	public Value min(){
		return min(root).key;
	}

	private Node min(Node x){
		if (x.left == null) return x;
		return min(x.left);
	}

	public Key floor(Key key){
		Node x = floor(root, key);
		if(x == null) return null;
		return x.key;
	}

	private Node floor(Node x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t;
		else return x;
	}

	public Node ceiling(){
		Node x = ceiling(root, key);
		if(x == null) return null;
		return x.key;
	}

	private Node ceiling(Node x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right, key);
		Node t = celing(x.left, key);
		if (t != null)	return t;
		else return x;
	}

//select(), rank()
	public Key select(int k){
		return select(root, key).key;
	}

	private Node select(Node x, int k){
		if (x == null)	return null;
		int t = size(x.left);
		if (t > k) return select(x.left, k);
		else if (t < k) return select(x.right, k-t-1);
		else return x;
	}

	public int rank(Key key){
		return rank(key, root);
	}

	private Node rank(Key key, Node x){
		if (x == null) return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)	return rank(key, x.left);
		else if (cmp > 0)	return rank(key, x.right) + 1 + size(x.left);
		else 	return size(x.left);

	}

//delete(), deleteMin(), deleteMax()
	public void deleteMin(){
		root = deleteMin(root);
	}

	private Node deleteMin(Node x){
		if (x.left == null)	 return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x; 
	}

	public void deleteMax(){
		root = deleteMax(root);
	}

	private Node deleteMax(Node x){
		if (x.right == null)	 return x.left;
		x.right = deleteMin(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x; 
	}

	//Hibbard
	public void delete(Key key){
		root = delete(root, key);
	}

	private Node delete(Node x, Key key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)	x.left = delete(x.left, key);
		else if (cmp > 0)	x.right = delete(x.right, key);
		else{
			if (x.right = null)	return x.left;
			if (x.left = null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

//keys()
	public Iterable<Key> keys(){
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<key>();
		keys(root, queue, lo, hi);
		return queue;
	}

	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if (x == null)	 return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = lo.compareTo(x.key);
		if (cmplo < 0)	keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)	queue.enqueue(x.key);
		if (cmphi > 0)	keys(x.right, queue, lo, hi);
	}
//print()
	private void print(Node x){
		if (x == null) return;
		print(x.left);
		StdOut.print(x.key);
		print(x.right);
	}




}