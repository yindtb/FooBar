/*
This problem has a similar context given in LC:
https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description

Given a string of 1's, we can intepret the combinations of 1's as follows:
(1->a, 11->b, 111->c, 1111->a, ...)
Output all the possible intepretations.
For example, 111 can be intepreted as c, ab, ba, aaa.
*/

package idontknow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Christina on 2/29/16.
 */
public class PhoneDial {
    /**
     * (1->a, 11->b, 111->c, 1111->a)
     * */

    //O(the length of the result)
    public List<String> phoneDial(String s) {
        int len = s.length();
        if (len == 0) return new ArrayList<>();
        String[] str = new String[]{"c", "a", "b"};
        List<Set<String>> list = new ArrayList<>();
        list.add(null);
        for (int i = 1; i <= len; i++) {
            Set<String> set = new HashSet<>();
            set.add(str[i % 3]);
            for (int j = 1; j < i; j++) {
                for (String s1 : list.get(j)) {
                    for (String s2 : list.get(i - j)) {
                        set.add(s1 + s2);
                        set.add(s2 + s1);
                    }
                }
            }
            list.add(set);
        }
        return new ArrayList<>(list.get(len));
    }
	
	// More efficient
	public List<String> phoneDial2(String s) {
        int len = s.length();
        if (len == 0) return new ArrayList<>();
        String[] str = new String[]{"c", "a", "b"};
        List<Set<String>> list = new ArrayList<>();
        list.add(null);
        for (int i = 1; i <= len; i++) {
            Set<String> set = new HashSet<>();
            set.add(str[i % 3]);
            for (int j = 1; j < i; j++) { // Now only two for loops
                for (String s1 : list.get(i - j)) {
                    set.add(s1 + str[j % 3]);
                }
            }
            list.add(set);
        }
        return new ArrayList<>(list.get(len));
    }

    public static void main(String args[]) {
        PhoneDial a = new PhoneDial();
        System.out.println(a.phoneDial("1"));
        System.out.println(a.phoneDial("11"));
        System.out.println(a.phoneDial("111"));
        System.out.println(a.phoneDial("1111"));
    }

}
