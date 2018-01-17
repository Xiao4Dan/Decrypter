
/**
 * This is a decrypter for decrypting a paragraph
 * every 64 characters using the given key
 * Ryerson FALL2016 CPS109 A_S_
 * V.1
 */
import java.util.*;
import java.io.*;
public class Decrypter
{
    public static void main(String args[])
    {
        //initialization
        Scanner in = new Scanner(System.in);//scanner in
        ArrayList<String> input = ReadFile("input.txt");//read and store the input file using readfile method
        //ArrayList<String> input = ReadFile("test.txt");//test case
        ArrayList<String> output = new ArrayList<String>();//for final result
        System.out.println("Please enter the correct key for encryption(Case sensitive):");//prompt
        System.out.println("(Click enter again without input the key will be 'ABCDEFGH' as default)");//prompt
        String key = in.nextLine();
        if(key.isEmpty())
        {
            key = "ABCDEFGH";
        }
        int hash = key.hashCode();//get the has code for encryption from the key
        String[][] transp;//2D array for storing temporary transposition result
        for(int lines = 0; lines < input.size(); lines++)//decrypt line by line from the encrypter file
        {
            transp = Transpose(transPattern(hash),input.get(lines));//reversed transposition method(2D String array)
            String subst = Substitute(hash, arrayToString(transp));//reversed substitution method(String)
            output.add(subst);//add each resulted line input output(String ArrayList)
        }
        WriteFile(output);//write file after all it's done
        //
        //******************************fun display******************************
        int fun = 0;//fun num
        Random numGen = new Random();//randomization
        System.out.print("Decrypting...");//prompt
        for(int x = 0; x < 10; x++)
        {
            for(int z = 0; z < 999999999; z++)
            {
                //Do nothing, just to make time and let the program look awesome
            }
            System.out.append(".");//fun effect
        }
        System.out.print("\n");//next line
        System.out.append("Writing File.");
        for(int x = 0; x < 10; x++)
        {
            for(int z = 0; z < 999999999; z++)
            {
                //Do nothing, just to make time and let the program look awesome
            }
            System.out.append(".");
        }
        System.out.println("");
        for(int w = 0; w < 25; w++)
        {
            for(int y = 0; y < 20; y++)
            {
                fun = numGen.nextInt(2);//randomize between 0 and 1
                System.out.append(" " + fun);
            }
            for(int z = 0; z < 10000000; z++)
            {
                //Do nothing, just to make time and let the program look awesome
            }
            System.out.println("");
        }
        //
        System.out.println("FILE WRITTEN");
        //
    }

    public static ArrayList ReadFile(String fName)
    {
        String temp = "";//temporary string to store each line of code
        ArrayList<String> lines = new ArrayList();
        File inputFile = new File(fName);//the textfiles the program is going to read
        try
        {
            FileReader fRead = new FileReader(inputFile);//just cliche
            BufferedReader bufferedReader = new BufferedReader(fRead);//just cliche
            while ((temp = bufferedReader.readLine()) != null && temp.length() == 64)
            {
                lines.add(temp);//as lone as each line has 64 characters, add the line into array list
            }
            bufferedReader.close();//close it to save energy for the computer
        }
        catch (IOException ex)
        {
            //don't bother to explain all the situation that goes wrong with the file
            //I just know there's something wrong
            System.out.println("SOMETHING WENT WRONG WITH FILE: " + inputFile);
        }
        return lines;//return the arraylist of string hold all the useful lines in the file
    }

    public static void WriteFile(ArrayList<String> secret)
    {
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
            for (int i = 0; i < secret.size(); i++)
            {
                out.write(secret.get(i) + "\n");//print each line of the output in the file
            }
            out.close();//close the buffer writer
        }
        catch (IOException e) 
        {
            System.out.println("OUTPUT.TXT NOT FOUND");//file does not exist
            System.out.println("YOUR RESULT OF THE FOLLOWING:");//then to show the result, just print out in the window
            for(int j = 0; j < secret.size(); j++)
            {
                System.out.println(secret.get(j));//each line of the arraylist
            }
        }
    }

    public static String[][] Transpose(int[] pattern, String trans)
    {
        String[][] matrix = new String[8][8];//8X8 2D array for 64 characters
        char[] re = new char[64];//64 array for 64 characters
        int a = 0;//initializing the value
        for(int i = 0; i < pattern.length; i++)
        {
            for(int j = 0; j < pattern.length; j++)
            {
                matrix[j][pattern[i]] = String.valueOf(trans.charAt(a));//read the 64 characters from left to right
                //and stretch it into the 8X8 2D array
                a++;//counter for trans spots
            }
        }
        return matrix;//return the 2D array
    }

    public static String arrayToString(String[][] matrix)
    {
        StringBuffer re = new StringBuffer();//String buffer is so much easier for pulling a paper to one line
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix[0].length; j++)
            {
                //re[i+j] = matrix[i][j];
                re.append(matrix[i][j]);//read the 2D array from top to bottom, left to right
                //and use the string buffer to combine them in one line
            }
        }
        return re.toString();//return the stretch string
    }

    public static int[] transPattern(int hash)
    {
        int[] init = {0,1,2,3,4,5,6,7};//using hash to get the pattern of encryption
        int a, b, temp;//temporary variables for switching numbers
        Random random = new Random(hash);//randomize using hash code
        for(int i = 0; i < 100; i++)
        {
            //ONE HUNDRED TIMES DUDE!
            temp = init[a=random.nextInt(8)];//store it temporarily
            init[a] = init[b=random.nextInt(8)];//over write the temporary char
            init[b] = temp;//put back the temporary char
        }
        return init;//return the transposition pattern
    }

    public static String Substitute(int hash, String subs)
    {
        char[] in = subs.toCharArray();//cut the string into char array for editing
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";//default alphabet
        for(int j = 0; j < subs.length(); j++)//actually the value is 64 i believe
        {
            for(int k = 0; k < original.length(); k++)//26 alphabet + 1 space. it's 27 i believe
            {
                if(subs.charAt(j) == subPattern(hash).charAt(k))//once 
                {
                    in[j] = original.charAt(k);
                    k = 27;
                    //input.charAt[j] = alphabet.charAt[k];
                }
                else if(k == 26)//exception
                {
                    in[j] = '@';//*this one is actually for encryption procedure,,,replace unknown characters with @
                }
            }
        }
        subs = String.valueOf(in);//convert the char array to string
        subs = subs.replace('@','O');//*and then replace the unknown character back as O,,,which is not really useful in decryption
        return subs;//return the good substitution string, which is basically the result
    }

    public static String subPattern(int hash)
    {
        Random random = new Random(hash);//randomize using hash code
        String ptrn = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";//for switching characters, will be changed
        String original = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";//original alphabet
        String a,b;//temporary string
        for(int i = 0; i < 100; i++)
        {
            a = String.valueOf(ptrn.charAt(random.nextInt(27)));//temp
            b = String.valueOf(ptrn.charAt(random.nextInt(27)));//temp
            ptrn = ptrn.replaceAll(a,"&&");//replace the it with && so that it won't confuse
            ptrn = ptrn.replaceAll(b,a);//overwrite the backupped String(char in fact)
            ptrn = ptrn.replaceAll("&&",b);//replace && with backupped String(char in fact)
        }
        return ptrn;//return the substitution pattern
    }
}
