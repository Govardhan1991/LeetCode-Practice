package TwitterChallenge;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        /*
        InputStreamReader in = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(in);
      String name = br.readLine();
         */

        /*
        int[][] myList = {{4,3},{1,2},{1,3}, {1,4}, {5,6}};
        int[][] tweetEdges = {{2,10}, {3,10}, {4,10}, {2,11}, {3,12}, {4,11}};
        int[] res = getRecommendedTweets(myList, tweetEdges, 1 ,3);
        */
        String start = "AAAAAAAA", end = "GGAAAAAA";
        String[] bank = {"GAAAAAAA", "AAGAAAAA", "AAAAGAAA", "GGAAAAAA"};
        System.out.println(findMutationDistance(start, end, bank));

    }

    static int findMutationDistance(String start, String end, String[] bank) {

        //Create set to make it easy for querying
        Set<String> dnaBank = new HashSet<>();
        for (String str : bank) {
            dnaBank.add(str);
        }
        dnaBank.add(start);
        dnaBank.add(end);

        //Create a queue and Do BFS to get minimum distance
        LinkedList<DNASeq> dnaQueue = new LinkedList<DNASeq>();
        dnaQueue.add(new DNASeq(start, 0));

        char[] possibleChars = {'A', 'C', 'T', 'G'};
        while (!dnaQueue.isEmpty()){

            DNASeq topSeq = dnaQueue.remove();
            String topDNAStr = topSeq.dna;
            char[] topDNA = topDNAStr.toCharArray();

            char ch = ' ';
            if(topDNAStr.equals(end))
                return topSeq.distance;

            for(int i=0; i<topDNA.length; i++){
                for (int j=0; j<possibleChars.length; j++){
                     ch = possibleChars[j];
                     char temp = topDNA[i];

                     if(ch!=topDNA[i]){
                         topDNA[i]=ch;
                     }

                     String newDNAStr = new String(topDNA);
                     if(dnaBank.contains(newDNAStr)){
                         dnaQueue.add(new DNASeq(newDNAStr, topSeq.distance+1));
                         dnaBank.remove(newDNAStr);
                     }

                     topDNA[i]=temp;
                }
            }
        }
        return -1;
    }

    static class DNASeq{
        String dna;
        int distance;

        public DNASeq(String dna, int dist)
        {
            this.dna = dna;
            distance = dist;
        }
    }


    static int[] getRecommendedTweets(int[][] followGraph_edges, int[][] likeGraph_edges,
                                      int targetUser, int minLikeThreshold) {
        Set<Integer> targetFollowingUsers = new HashSet<>();
        int[] userFlw;
        //Create set of whom targetUser is following
        for(int i=0; i<followGraph_edges.length; i++)
        {
            userFlw = followGraph_edges[i];
            if(targetUser==userFlw[0]){
                targetFollowingUsers.add(userFlw[1]);
            }
        }

        Map<Integer, Integer> tweetLikes = new HashMap<Integer, Integer>();
        int[] userTweetLike;
        //Create list of tweets for those followed by target
        for(int i=0; i<likeGraph_edges.length; i++)
        {
             userTweetLike = likeGraph_edges[i];
             if(targetFollowingUsers.contains(userTweetLike[0]))
             {
                 tweetLikes.put(userTweetLike[1], tweetLikes.getOrDefault(userTweetLike[1], 0)+1);
             }
        }

        //Sort tweets based on # of likes
        tweetLikes = sortByValue(tweetLikes);

        //Check how many tweets crossed threshold
        Iterator itr = tweetLikes.entrySet().iterator();
        List<Integer> returnArray =  new ArrayList<Integer>();
        while(itr.hasNext())
        {
            Map.Entry pair = (Map.Entry)itr.next();
            if((int)pair.getValue()>=minLikeThreshold)
                returnArray.add((int)pair.getKey());
        }

        //Convert to int[] using lambda
        int[] returnTweets = ( returnArray.stream().mapToInt(i -> i).toArray());
        return returnTweets;
    }

    public static Map<Integer, Integer>
    sortByValue(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        });

        Map<Integer, Integer> result = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
