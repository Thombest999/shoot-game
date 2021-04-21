package sample;

import java.util.ArrayList;

public class arrayMethod {
    public Integer max(ArrayList<Integer> arr)
    {
        Integer max = arr.get(0);
        for (int i =1;i < arr.size(); i++)
        {
            if (arr.get(i) > max)
            {
                max = arr.get(i);
            }
        }
        return max;
    }
    public Integer min(ArrayList<Integer> arr)
    {
        Integer min = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i) < min) {
                min = arr.get(i);
            }
        }
        return min;
    }
    public Integer sum(ArrayList<Integer> arr)
    {
        Integer sum = 0;
        for (int i = 0; i < arr.size(); i++)
        {
            sum = sum + arr.get(i);
        }
        return sum;
    }
    public Integer mean(ArrayList<Integer> arr)
    {
        return sum(arr) / (arr.size() - 1);
    }
    public Integer mode(ArrayList<Integer> arr)
    {
        int temp = 0;
        int count = 0;
        int tempcount = 0;
        for(int i = 0;i < arr.size(); i++)
        {
            for(int j = 0;j < arr.size(); j++)
            {
                if(arr.get(i).equals(arr.get(j)))
                {
                    count ++;
                }
            }
            if(count > tempcount)
            {
                tempcount = count;
                temp = arr.get(i);
            }
            count = 0;
        }
        return temp;
    }
    public String common(ArrayList<String> arr)
    {
        String temp = "";
        int count = 0;
        int tempcount = 0;
        for(int i = 0;i < arr.size(); i++)
        {
            for(int j = 0;j < arr.size(); j++)
            {
                if(arr.get(i).equals(arr.get(j)))
                {
                    count ++;
                }
            }
            if(count > tempcount)
            {
                tempcount = count;
                temp = arr.get(i);
            }
            count = 0;
        }
        return temp;
    }
    public boolean contain(ArrayList<Integer> arr, Integer int1)
    {
        for (int i = 0;i < arr.size(); i++)
        {
            if(int1.equals(arr.get(i)))
            {
                return true;
            }
        }
        return true;
    }
    public String longest(ArrayList<String> arr)
    {
        int now = 0;
        int temp = 0;
        String word = "";
        for (int i = 0;i < arr.size(); i++)
        {
            now = arr.get(i).length();
            if(now > temp)
            {
                temp = now;
                word = arr.get(i);
            }
        }
        return word;
    }
    public String shorter(ArrayList<String> arr)
    {
        int now = 0;
        int temp = 100;
        String word = "";
        for (int i = 0;i < arr.size(); i++)
        {
            now = arr.get(i).length();
            if(now < temp)
            {
                temp = now;
                word = arr.get(i);
            }
        }
        return word;
    }
    public Integer freqcontain(ArrayList<Integer> arr, Integer int1)
    {
        int count = 0;
        for (int i = 0;i < arr.size(); i++)
        {
            if (int1.equals(arr.get(i)))
            {
                count++;
            }
        }
        return count;
    }
    public boolean divis(ArrayList<Integer> arr, Integer int1)
    {
        Integer lol = 0;
        for(int i = 0; i < arr.size(); i++)
        {
            if((arr.get(i)%int1) == lol)
            {
                return true;
            }
        }
        return false;
    }
    public boolean divisAll(ArrayList<Integer> arr, Integer int1)
    {
        Integer lol = 0;
        int count = 0;
        for(int i = 0; i < arr.size(); i++)
        {
            if((arr.get(i)%int1) == lol)
            {
                count++;
            }
        }
        if(count == arr.size())
        {
            return true;
        }
        else return false;
    }
    public boolean smalltobig(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) < arr.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public boolean bigtosmall(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public boolean dublicate(ArrayList<Integer> arr)
    {
        for (int i = 0; i < arr.size(); i++)
        {
            for (int j = i + 1; j < arr.size(); j ++)
            {
                if (arr.get(i).equals(arr.get(j)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<Integer> shiftright(ArrayList<Integer> arr, int shift)
    {
        int n = -1;
        while(n < shift)
        {
            Integer temp = arr.get(0);
            for (int i = 0; i < arr.size(); i++)
            {
                arr.set(i, i + 1);
            }
            arr.set(arr.get(arr.size() - 1), temp);
            n++;
        }
        return arr;
    }
    public ArrayList<Integer> shiftleft(ArrayList<Integer> arr, int shift)
    {
        int n = -1;
        while(n < shift)
        {
            Integer temp = arr.get(arr.size() - 1);
            for (int i = arr.size() - 1; i > 0; i--)
            {
                arr.set(i, i - 1);
            }
            arr.set(0, temp);
            n++;
        }
        return arr;
    }
    public ArrayList<Integer> reverse(ArrayList<Integer> arr)
    {
        ArrayList<Integer> lol = new ArrayList<>();
        for (int i = arr.size() - 1; i > 0; i--)
        {
            lol.add(arr.get(i));
        }
        return lol;
    }
}
