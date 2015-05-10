/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.Arrays;
import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Random;
import java.util.HashMap;
import java.util.TreeSet;
//import java.util.Hashtable;
//import myGraph.myGraph;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Comparator;
//import java.util.StringBuilder;
import java.util.HashSet;
//import java.util.TreeSet;
//    import java.util.Collections;

/**
 *
 * @author Emma
 */
public class StringOp {
      List<String> res= new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        //first always (, d last always ). then switch between whatever
         if(n==1) { res.add("()"); return res; }
        char[] op= new char[2*n]; //list all permutation
        for(int i=0;i<n;i++){
            op[i]   = '(';
            op[n+i] = ')';
        }
         generateParenthesis(op, 0);//list all permutation
         return res;
    }
    
    HashMap<String, Integer> map= new HashMap<>();
    public int numDecodings(String s) {
       
        return helper(s, 0);
    }
    public int helper(String s, int start) {
        if(s==null||s.isEmpty() ) return 0;
        if(start>=s.length()) return 1;
        if(map.containsKey(s.substring(start))) return map.get(s.substring(start)); 
        int n = s.length();
        int res=0, first = 0, sencond=0;
        // for(int i=start;i<n;i++){
            char cur = s.charAt(start);
//            if(cur=='0') { 
//                map.put(s.substring(start), -1); 
//                return -1; 
//            }
            
            if(cur>='1'&&cur<='9') {
//               first = helper(s, start+1)==-1 ?  -1: helper(s, start+1);                
               first = helper(s, start+1);
            }
            
            if(start+1<n && (cur=='1' || ( cur=='2' && s.charAt(start+1)>='0' && s.charAt(start+1)<='6'))) {
//                sencond = helper(s, start+2)==-1 ? -1 : helper(s, start+2); 
                sencond = helper(s, start+2); 
            }
        
        res = first+sencond  ;
        map.put(s.substring(start), res);
        return res;
    }
  
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if(words==null) return res;
        // if(words.length==0||words[0].isEmpty()) { res.add("");return res;}
        int curl=0;//words[0].length();
        int start =0;
        for(int i=0;i<words.length;i++){
            // if(words[i].isEmpty()) continue;
            if(curl<maxWidth && curl!=0&& curl + words[i].length()+1 > maxWidth){  //word[start] to word[i-1] to be in one line with extra space //
                StringBuilder sb= new StringBuilder();
                if(i-start-1>0){
                    int extraEach = (maxWidth-curl)/(i-start-1);
                    int extra = (maxWidth-curl)%(i-start-1);
                    for(int t=start;t<i-1;t++){
                        sb.append(words[t]).append(" ");
                        if(t-start<extra)
                            sb.append(" ");
                        for(int m=0;m<extraEach;m++)
                            sb.append(" ");
                    }
                }
                sb.append(words[i-1]);
                if(i-start-1==0){
                    while(sb.toString().length()<maxWidth)
                        sb.append(" ");
                }
                res.add(sb.toString());
                
                curl= 0; //words[i].length();
                start=i;
                i--;
                // if(i==words.length-1) i--;
            }else if(i==words.length-1 || (curl!=0&&curl + words[i].length()+1 == maxWidth) ||(curl==0&&curl+words[i].length()==maxWidth)){ //just right for one line, or last word,no extra space
                StringBuilder sb= new StringBuilder();
                for(int t=start;t<i;t++)
                    sb.append(words[t]).append(" ");
                sb.append(words[i]);
                if(i==words.length-1 ){
                    while(sb.toString().length()<maxWidth)
                        sb.append(" ");
                }
                res.add(sb.toString());
                curl=0;
                start=i+1;
            }else { //need more word
                if(curl==0) { curl = words[i].length(); start=i;}
               else  curl += words[i].length() +1;
            }
        }
        return res;
    }
    
    public String multiply(String num1, String num2) {
        if(num1==null||num2==null) return null;
        if(num1.equals("0")||num2.equals("0")) return "0";
        StringBuilder res = new StringBuilder();
//        res.append(0);
        int n = num2.length();
        char[] c2=num2.toCharArray();
        for(int i=0;i<n;i++){
            String subres= multiply(num1, c2[i]-'0');
            res = add( res.append(0).toString(), subres );
        }
        
        return res.toString();
    }
    public String multiply(String num1, int t) {
        int m = num1.length();
        char[] c1=num1.toCharArray();
        
        StringBuilder res = new StringBuilder();
        int carryover=0;
        int i = 1;
        while( i<=m || carryover>0 ){
            int d = ( m-i>=0 ? c1[m-i]-'0' : 0)*t + carryover;
            res.insert( 0, d%10+"" );
            carryover = d/10;
            i++;
        }
        return res.toString();
    }
    public StringBuilder add(String num1, String num2) {
        int carryover = 0;
        int m = num1.length();
        int n = num2.length();
        char[] c1=num1.toCharArray();
        char[] c2=num2.toCharArray();
        int l = Math.max(m, n);
        int i = 1;
        StringBuilder res = new StringBuilder();
        while( i<=l || carryover>0 ){
            int d = ( m-i>=0 ? c1[m-i]-'0' : 0)+( n-i>=0 ? c2[n-i]-'0': 0 ) +carryover;
            res.insert(0 , d%10+"");
            carryover = d/10;
            i++;
        }
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        if(board==null||word==null||board.length==0||board[0].length==0) return false;
        int m = board.length;
        int n = board[0].length;
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0) && existHelper(board,"", i, j, word) ){
                    count++;
//                    return true;
                }
            }
        }
        System.out.println( "count:"+count);
        return false;
    }
     
     public boolean existHelper(char[][] board, String prefix, int x, int y, String word) {
        if(board==null||word==null||board.length==0||board[0].length==0) return false;
        int m = board.length;
        int n = board[0].length;
        if(word.length()==1 && board[x][y]==word.charAt(0) ) {System.out.println(prefix+""+word); return true;}
        // LinkedList<Integer> q = new LinkedList<>();
        // int idx= x*n+y;
        // q.add(idx);
        // while(!q.isEmpty()){
            // int p = q.remove();
            int s=x;// p/n;
            int t=y;// p%n;
            if(board[s][t]==word.charAt(0) ) { 
                board[s][t]='_'; 
                if(s>0   && board[s-1][t]==word.charAt(1) &&existHelper(board,prefix+""+ word.substring(0,1), s-1, t, word.substring(1) )) return true;
                if(s<m-1 && board[s+1][t]==word.charAt(1) &&existHelper(board,prefix+""+word.substring(0,1), s+1, t, word.substring(1) )) return true;
                if(t>0   && board[s][t-1]==word.charAt(1) &&existHelper(board, prefix+""+word.substring(0,1),s, t-1, word.substring(1) )) return true;
                if(t<n-1 && board[s][t+1]==word.charAt(1) &&existHelper(board,prefix+""+word.substring(0,1), s, t+1, word.substring(1) )) return true;
                
                board[s][t]=word.charAt(0);  // change it back
            // if match, continue to find next
            }
        // }
        return false;
    } 
     
     public int  howmanyexist(char[][] board, String word) {
        if(board==null||word==null||board.length==0||board[0].length==0) return 0;
        int m = board.length;
        int n = board[0].length;
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0)  ){
                    count += howmanyexistsHelper(board,"", i, j, word);
//                    return true;
                }
            }
        }
        System.out.println( "count:"+count);
        return count ;
    }
     
     public int  howmanyexistsHelper(char[][] board, String prefix, int x, int y, String word) {
        if(board==null||word==null||board.length==0||board[0].length==0||word.length()==0) return 0;
        int m = board.length;
        int n = board[0].length;
        if(x<0||x>=m|| y<0||y>=n) return 0;
        if(word.length()==1 && board[x][y]==word.charAt(0) ) {
            System.out.println(prefix+""+word); return 1;
        }
        int count=0;
//        LinkedList<Integer> q = new LinkedList<>();
//         int idx= x*n+y;
//         q.add(idx);
//         while(!q.isEmpty()){
//             int p = q.remove();
            int s=x;// p/n;
            int t=y;// p%n;
            if(board[s][t]==word.charAt(0) ) { 
                board[s][t]='_'; 
                count +=howmanyexistsHelper(board,prefix+""+ word.substring(0,1), s-1, t, word.substring(1));
                count +=howmanyexistsHelper(board,prefix+""+word.substring(0,1), s+1, t, word.substring(1));
                count +=howmanyexistsHelper(board, prefix+""+word.substring(0,1),s, t-1, word.substring(1));
                count +=howmanyexistsHelper(board,prefix+""+word.substring(0,1), s, t+1, word.substring(1));
//                if(s>0   && board[s-1][t]==word.charAt(1) &&existHelper(board,prefix+""+ word.substring(0,1), s-1, t, word.substring(1) )) return true;
//                if(s<m-1 && board[s+1][t]==word.charAt(1) &&existHelper(board,prefix+""+word.substring(0,1), s+1, t, word.substring(1) )) return true;
//                if(t>0   && board[s][t-1]==word.charAt(1) &&existHelper(board, prefix+""+word.substring(0,1),s, t-1, word.substring(1) )) return true;
//                if(t<n-1 && board[s][t+1]==word.charAt(1) &&existHelper(board,prefix+""+word.substring(0,1), s, t+1, word.substring(1) )) return true;
                
                board[s][t]=word.charAt(0);  // change it back
            // if match, continue to find next
//            }
         }else return 0;
        return count;
    }
    
     public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res= new ArrayList<>();
        if(s==null||s.isEmpty()||words==null||s.length()<words.length
            || s.length()<words[0].length()*words.length)  
            return res;

        int m= s.length();
        int n=words.length;
        int count=0;
        HashMap<String,Integer> dict=new HashMap<>();
        for(String each: words)
            dict.put(each, dict.containsKey(each)? dict.get(each)+1 : 1);
            
        int wl=words[0].length();
        for(int i=0;i+wl<=m;i++){
            if( dict.containsKey(s.substring(i,i+wl)) ){
                HashMap<String,Integer> visited=new HashMap<>();
                for(int j=0;j<n  && i+j*wl<m;j++){
                    String cur =s.substring(i+j*wl,i+(j+1)*wl);
                    if(!dict.containsKey(cur)) break;
                    visited.put(cur, visited.containsKey(cur)? visited.get(cur)+1 : 1);
                }
                if(visited.equals(dict))
                    res.add(i-wl*n+wl);
            } // else { i++; visited.clear();}
        }
        
        return res;
    }
     public String getPermutation(int n, int k) {
        List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
     
    public void sortString(String[] a){
        
         String[] ss = {"2","20","12","9","300","211","2999","200" };
         Arrays.sort(a);
         System.out.println(Arrays.asList(a));
         
         Arrays.sort(ss, (String a1, String b) -> -a1.compareTo(b));
          Arrays.sort( ss,(String s1, String s2) -> (s2+s1).compareTo( s1+s2 ));
         System.out.println(Arrays.asList(ss));
    }
    public String largestNumber(List<Integer> num) {
        if (num==null||num.isEmpty()) return "";
        if (num.size()==1) return num.get(0)+"";
        
        String[] ops=new String[num.size()];
        int j =0;
        for(Integer i:num)
            ops[j++]= i+"";
            
         Comparator<String> largest = new Comparator<String>(){
            public int compare(String a, String b){
                String s1= a+b;
                String s2= b+a;
                return s2.compareTo(s1);
            }
        };
        
        Arrays.sort(ops, largest);
        
        StringBuilder res = new StringBuilder();
            
            
        for(String cur : ops){
            res.append(cur);
        }
        return res.toString();
    }
    public String testminW(){
    StringBuilder S=new StringBuilder();
    S.append("kgfidhktkjhlkbgjkylgdracfzjduycghkomrbfbkoowqwgaurizliesjnveoxmvjdjaepdqftmvsuyoogobrutahogxnvuxyezevfuaaiyufwjtezuxtpycfgasburzytdvazwakuxpsiiyhewctwgycgsgdkhdfnzfmvhwrellmvjvzfzsdgqgolorxvxciwjxtqvmxhxlcijeqiytqrzfcpyzlvbvrksmcoybxxpbgyfwgepzvrezgcytabptnjgpxgtweiykgfiolxniqthzwfswihpvtxlseepkopwuueiidyquratphnnqxflqcyiiezssoomlsxtyxlsolngtctjzywrbvajbzeuqsiblhwlehfvtubmwuxyvvpwsrhutlojgwktegekpjfidgwzdvxyrpwjgfdzttizquswcwgshockuzlzulznavzgdegwyovqlpmnluhsikeflpghagvcbujeapcyfxosmcizzpthbzompvurbrwenflnwnmdncwbfebevwnzwclnzhgcycglhtbfjnjwrfxwlacixqhvuvivcoxdrfqazrgigrgywdwjgztfrbanwiiayhdrmuunlcxstdsrjoapntugwutuedvemyyzusogumanpueyigpybjeyfasjfpqsqotkgjqaxspnmvnxbfvcobcudxflmvfcjanrjfthaiwofllgqglhkndpmiazgfdrfsjracyanwqsjcbakmjubmmowmpeuuwznfspjsryohtyjuawglsjxezvroallymafhpozgpqpiqzcsxkdptcutxnjzawxmwctltvtiljsbkuthgwwbyswxfgzfewubbpowkigvtywdupmankbndyligkqkiknjzchkmnfflekfvyhlijynjlwrxodgyrrxvzjhoroavahsapdiacwjpucnifviyohtprceksefunzucdfchbnwxplhxgpvxwrmpvqzowgimgdolirslgqkycrvkgshejuuhmvvlcdxkinvqgpdnhnljeiwmadtmzntokqzmtyycltuukahsnuducziedbscqlsbbtpxrobfhxzuximncrjgrrkwvdalqtoumergsulbrmvrwjeydpguiqqdvsrmlfgylzedtrhkfebbohbrwhnhxfmvxdhjlpjwopchgjtnnvodepwdylkxqwsqczznqklezplhafuqcitizslzdvwwupmwqnlhxwlwozdogxekhasisehxbdtvuhrlucurbhppgsdoriyykricxpbyvxupencbqwsreiimclbuvbufudjrslsnkofobhptgkmmuuywizqddllxowpijhytvdkymzsulegfzfcjguojhzhxyyghhgbcllazmuuyzafahjjqgxznzinxgvgnbhrmuuljohjpkqpraahgajvzriyydengofskzgtppefzvwrvxadxjaydjydocqvsxpdyxyondvmyrfvqiaptanwllbaquxirmlqkmgzpbnputmldmcwoqvadwavqxeilraxdiwulmlffxsilvgcnbcsyeoqdsaolcorkmlxyzfdyznkuwmjxqcxusoxmqlxtzofocdmbiqzhflebzpbprajjqivhuvcvlhjnkwquosevfkzfzcwtcietqcamxcikltawrsshkydsiexkgvdidjbuldgkfqvrkxpdpjlakqsuurecmjkstomgrutzlqsxnjacuneedyzzrfbgpoykcmsvglwtdoqqztvugzakazlrhnxwdxifjccsozlrpckpxfldglpgnbauqzstxcaiecaudmotqyknfvsliiuvlurbvjwulwdsadmerazjyjydgrrobnmmjdpeplzcjcujhhpbhqmizlnhcgwftkrcnghctifcmbnvifwsvjcxwpeyycdrmwucedexnlbznquxvtpretoaluajxfajdwnhbugofjpuzmuxflolfenqynzxubjxawgbqmsyvhuwvotaajnfpaxqnwnjzwgzvmdnaxlxeiucwpcyzqfoqcegaspcqybnmgbndomkwgmvyqvxgblzfshimykeynslutaxjmjgvvdtmysubfvjxcrjddobsgombomkdvsatvvfwnzxsvgszzbccrgxzulclzpqvhsqfnvbcwywrfotgsxlldilatnntcfqmxgrkdsozsktzbogtlrerzrtuhiplnfxknqwsikudwncxdiqozxhaoavximjvuihjzdcjpwmmlploxeezbmzrmwrxlauficojhqtxohlzwwpwcuvfgwzuvqrgqmlaozmxshuiohingzjitgobcnwzdpfvdsxrujroqlwhvgubgdlzjzdnozptqwqurqnlzezssvznctokybljdoyrppngmdcdvpqvuppmmqbqlrajsmuvcupskcawhcbdrrangrbuhcnstndobzjgtyydcabkccpvtpjbgmyprljkamaelkkgkkmnknzosojnfupnhncyalkazlemxoshaewkuvymjkzqeqdlfflfsygrjmdidypdcmoyjoktykldapqiwenpcviniovnmkqqygpivbdvloaoftwcxltzhbmrrhedtuuudleamjvaxwqfrohozcpidbzxkfafcwbfjffwocyoaotrccfdeumjxngjthrvfsapyhnojlcmbxddzlidhwnhktqdcjykcazcjoqszveaskfsvnxckkjwgczknzupbvtkjmeihlkzvptqfrurdgnjkouxpqpqmicvugebrqdmgyenpbethrerhaqwrfodoqaiyemqxredpjqhkwctpgmwjcsaiifyyfiwmuojntmdeemqaolpwxnfbffjpmjnssleocncxbhbhttjjeyfdllessqjfzwxtjdilsmivvlcqglzmlepyrwskmbrnzqhivrwnfgiasmsaxrnkxeipaaboduccklmfckuhrcjlqblnuaxrfhihjlwofyqrleynpswiwhvmigbejavojgvsrtgztysefrrulpekwzwghakqaigkocehxnirlbvqspmfgqpdrolzowrqgycuchdzumqhnmyhdmojfeowsaxiypyenbapidoerhletlnyychdgwbayziwoicbjcsthixzplfkwtiwvsbdodfocpksxmvhqnczvaylnexjxgguyhzomecotoiqcdzuqctoesbrwyavgiresquewyvrmdhqhjkzleppwqgupirxtkcncytyxqpjuyadhmeuqulomtidcbbxlfmndfnawcmsdoxkadhtzshmmsrotsnfxzudgifcmtwpjtamzhfybmkneedawqhwrbzyjgawaznjunvtwcsypenvirvhhcdbgezrkbnmadyvsvopyippnckxviedmjgsnfkaizmjckgviwmghdvwhhtdpaicjowxvgzdglokyufgtroawjwesrhirrmbfiacrzfzmujmqpujiilftjlmdswulkxquzslyzkltuzmluxtcjawulkxfguqqrikrcwreiezeelpyjlaulyqziogqizgbhtsmrmqzqreudvsogviuvyveobuyadakwuwngomxfsrkomywhiqejlixnfwpiwzesxrznfwvapfobekkmdpxqzvdettopusjsliftgatwijzmvmaydypcvujopxfksocfxjydmrbuabiwpkwsklwfihtxhahzpleoxftjwvfzynxnzthkhfppnloulyvajbqigktdhyefnbunwdxfiowxzltljonuqgfwqybxzhemkybjdyolnnjmaczjtpfjvmhlllkkuoyhoaqrageptnetsdelumwpyfclsoxpewwsymlasqqofuhzxomucijaqookclzhjxbhjniefaukudkrrwlthxwznklpvnyfkaowpyauyugsxsmrrzmayiblsmdqzdxmfniuoiqoezjdtvukwhmxrnkkcncbtzpyoxvchnrlmarixnuvwhsowfayuccndrmrpjuwwnklnbtmqnhnwcbthbrbfvpkndbemxaikmvzpqlgettcxwvezpfgmwqzzrfnriybutdmkosqjnsglqkkhsqtogvqzadsibudvzphnjxxyrfjhsvokniyvdowfupovlrskizxtwwroecxwzmgmwghbxdgruumfnfpxensdlltpnqloeraayjdxpmojiapwhgvotorhbmypckdjdgjdrpagbosjrhhyndojthsutqlwcrfizqlqhozieruvabwpgmabusynljlncsvbljusztddkxbkyzbhlcifugthsexuxsykdsccnfixcljdkkkvmudjbwstcppbkplnhqsuvctanedxppudjxomvhhywzbgonwydquasoahsfejuangybsvkctjbxppknnpfirxyugszmcwnpcnswifycobbfrltgcaovaopghptjngartunbzofppgvitqpxqftquixbzqmmwmdrituijaxaiujckabbfwrbfqeggqveeaxntsxcuwfcrqgbqiexgybnofuxypdepbrltqpnnurgkyjcioaoobcciybgnflegerzvhokdfqofzsnpsedvgieejmtoxzuervzajldrbtwcmmsgqvcyfiepuzduayyrvztfkxylquneihlyfpykxczrddledlxykrfwofjgcznkgyllnjwkovdrarxfcvepmllatvivuvfcvsoickushylirjntetkqhwsatcvpyctvvheztardaenrncnrwxjfvbhqechegbzdifcegvhiauapwnhukqbiuiyamgethfwrnbvvyedyadozsxvfpxnlhllutrpaxnumorhnyknyqdziavpsucdbqpxjimmhaitqzadxltybwxlzsbrofwqxlnjwcvdcfxsexyektcnpbbqucjkjgahtqqpsntwssoyrocchgrzispewzoghrpajfqbulxmdnmuerylxqmhkenhfcpmvemelehfretwtftxwrlwjxfwdtdivuwsalwlpdsjjlfcqyapsnnbmsxqlcaiyxayiylzbdimoophorygelkqdhirmjzmgcloaynecyqsofbcyzjemtvscfjqokdumqknoarsyjnoroqpqbucwrwbwhtlswhgouvfuoxuykipbagreavatudqbxdvvmekgzpaqowobgfchlvlosnhotxsqcnnptxvtowlduzebgiirfvfzkpofmgxpvlgpibkxzvcuwivcqfvxcbwoqkueqrvmcbdnfnmeioaewxiocdlgehvwurdkkyypcdchqonaeoealmqqqbwwktvemyrxyrocvqlngzokpsmahcszfrvrmsyzsryrkmvfehvkgjwxdixkmsjtjhmvbkwubwnmiitopoaxxwudgunumznxiasjmrfqnscybxmsonqnlmquigowfetpeoasfgiuymsbmhuawmphagbjmsftwbkcpkuusdqrrjudqqdmetvfbzqprvkpwurnenjxsaqkjmnbdtphomorlegecqtammoqazpuzhekuunzpcidpxwcdcjhueigryytxqnzzujtqxufbdkscgxfkgpgkxmdxmwwemxegjzwgudjxncbvzifhxlrwzvtntssfpwnyxlgrosqduryvadcxqvupspdmjxtbvhbssimjacowwntysvvjsraljfxscqvxzxsuhedjirfegyczvakntkycqxxuqsuprmlysqhakofqojrjjbzdozhgxgapbwgskstciafsrkjfvapheqmsptaoccddzkxjeqomttfkfqpcsgjywvqopsctviwuuvfymvkhortvhiycrhapftdwlipgqlcmikkufwdwowtxhrbybcpgvvcidrpvethmdtjlpmhfjadqugqxifffchofafcgylueefpwuybdagvunntvuydxhrehwhpwukazrrvlpyqsflmsaipvguuolyxjhniczkdqcyetyuldiaxiipfojzexacghpqlqboidomwnhispkqzshfiqgnngpwqgmbwnqesgtrtabmrleqdlxldeatmrrcxfgvvycneveaxhoossgxfglimlbydudcajavhcilzpnwwbmrtuoaazjmlmlqhqshzseiwotxdjvckhteeheejprueemlwguvbydzmyxshswscpygyemhwfdajpnhyhczhhytivnpqjjsyjazqmgmsmoddblbipcpxbkhyawqjiiktkjjzrxrflwjmjmwbpnysahqafhjnrvjegsdaswfdsqsedofuefmemegrnrfhnolviovakdgetaiyonuusgyeneyawdjltugdkegwhobcojdezxztgzatgyvcdhpwbxbobhkixlnlxqqypprvotquroyuvpsynumodzzbmmmlecjvtdtiwjeozdiusdvhxhwcgxdvlsgpwqmqvfarrehqjsnevilurjwagcvrwbistviockitprkyjxcghqayzzygdtzzvirqfcfhmpbdgnesmgatydrycqgflheipxzwbggovjtdwxxigydedwefommausilphirpohmxasvypfepiksepzvblvvdfhvnrmehvrgvjbvagbsqmmwdmrezmcfslaheonljergpseqafkstwowiibkwfpoqrxwfnhqryyjsczukjmdfcaqkdchirxakxwpkfhbffkxkltuwfxehxwscybkpymzvkqfpzjuevtqjmkfrilbhhvkfwwwwjxutpzlokfviblrnwyhgkinrfzzbwxzhvtcmvnbhvwpwjilfhsntadmhclkyjkfgdksaxviutxqdgckpuyixbugfiretblzgvthvpppioilwmyliwvhzsoeafktgwumtnvqckinbqyxcwlkugstygaankttinhedfuhrcusstswrdjojbjkjjkyugtcvcgyhdgzfaravlwpohdaimktwwtscmwypdywigvnjppeaotrvyrglbbjzvbchxcwcctkjqashpykdubzssfdvgowbpalnchrhccsvekctkozepazjhegntdridcxilrpovjlzvnufctmttlfcpnqiqjtwnsgajxqegbdbrygvtuopfvrvjjfbsyxhrdkaaahickjtksoemetuakpjwwmqvkyopiqskxamkkhuexyqctkegbpcybsvnsjdcbvnzvbjhjfligekzoqhshlqjenwywbbxwqyurjbcpnlvrxuhqezxprgvefucgxcfnazgkalbpwwivqslwtlmlthrydwhaampcnyopjpfhlpcehqabdmowwhxzdecdsrihrwwambjxrmbaecbhqpfmxcmcioichqjbmgbyjlyczzdfbeoswvgvysziihlszwtocwomjmqkmtrpafjwdqtbksjvcwpdtkrxiglsuceivwyvdjtgbmjohvljrammmgkumvogztpvrpswaodeaosjjdsdfhprnblbzajyvavmpqksenwgcoqntkqytirglehketlbtiplyadepzntpdhkpxkjhbptfzfmsspnbfybfbiwcvqtyxdpwpyeqqzyrgklzbycgxdnankfiayizeyvtybmoakfjwsixrmgqptsffxnfywgcwcxudjjgvqrrjzralxscskhyixfitmkpqjjttubonsiwtbygaqlscuskrysmmedcopxjjbzytjupksxkkifnfxzyxuljyqloflzrmzpfikwveuhremejmfijzvtalgotrqkdpblznhwsdelisrtewdowyjwkpmdjcpmtqzvehrymxjwqaqwytmuuvsgnhlwjakfskayttwfrhejjufipsejpxrcecypeluxvwvqquxhdbqnxxxnpbyfyqjcukszvowsltibpfktjcggzdvrgwwfofjipdjmshefbmisuslfutlvyjmfhkkvstfhxpwrwawzpeslydquxdpvmeatomqgiwqflmyjmwjdadoaieufkldwpfseuimcgejtqhdfdoiftzfbfbjpmmmctginqwpxbysthxymljreiyinzrdmdrqyzampydozejvngtaueraosicrzhfcaxdzzrqyuhzaquoyqoswhtlzbprbyyfywizvbrvwyvyqrmpjajgicrjegaheboexzduauqyvnxngjcqmmwwqpfwvidbjufitkctgusbjrpqatiohekytsuqaatjuytpkvkqsesdvjvwedmmjxmepgupinaenvtxseqkiiogtlexpqlynxdeezvopvteqoejuuvfellxxpwofimvfrzlrvaisvmllswgmtlqfypenmsuugrbyrnropbgkptvipdujonqocudooykurmuibnwqofceyzoqdiztvpiylloblzxdnsnohmewtgcrfqhkaieyzowmbmnplluafhomvsioamiiersfaydboboqnbfbobbtiqgekqsldcthunouorvcsjuinbjbcmvlcdrptlayoviikweyqzdklxsabdbdqyqubhjxvlxjiftvexvuyyejyzlzcncuntprcaoxmniwtbrzynvxnbilaumdjrxykopopfodtwboyvpyikxxlilmcxrnoqlahdrwifbdberbzgahsxhefssjrjygbkiipzgxehdimujldjvxjebowtaneyvgkgqzfxzmgcusydgdpdbyjcohyowcpskzabbfyatecnzcthgimhgvlucllyqasazsdcadctkgjljcurmgaudnqhzbhpdarduxfwakqmqbxbfrurzuncidljhtykvrproxorhgdzhbdouxhusuycxkleflpccgttdjkkppmyqpmnmthgnhintvtygkclrweufqhxftvyiklwfudtdlixjbxpmaafygzfyicebaejmpirllmoyunhylzknbrlissgbxmcxqojhwcsjpjhjwphjotwpkjzfcigbwkynyjuwlpfaanmweviupelbqnguvovrzvgxedwrubuuqiqwdyqufcwxrermtofujotprpintchjkziqaykhcietnxhilpcudvlwntjgysrkrbaralyeteyibhsmwuibsvxhpippaizskalknkqiqqrsyjeugpwakvhbeyqggqyqskcfwtnmlggjqlgyceymzhqfhgmnwmgqykvufljrrpajcghgkhvmqltxrhlqutgsdepjpaairasujbcvjxhzidxckksedazgeopvqrehsybbbgykdimmllovxicaidiprakhrqqjqumsaledtsrgfhdcpfcailpbnbkeudokxcexplaifsqlbunrbstmdipslcwffmscpyzejbppvfcxbpqdhjrmtgeeibknnepecqjosafphhmjyeognfuglznwczlrddwfthvnjadqjrbiwpolwiskjhgngrhjudqqmmdvoinycpgagwldgfnqmtfsmmbjruzdgeawnqurfarlwcodidvuwcplpquimtosxormeldlouzkxnphrmdppsaglwoxpcsptjbzufnkbmvabmhublmsfoyqlooatiportpnacybovesjnmkaycgncwyrmakwsannpdnsdqiyuchipuipyqebpesdheazgpovsbpvtavqcfatzkjxbpaquutdyveelscdtrboryeudenepyesoeikzdpzztgmlkpgbsuvrzrwfgdufhmvnwhocwqdpypaigilzcgsdxqjetflcuiqspgulwxcuoeevmquowouwedcakncevuzkbtxztbwhfacrqyhnoblvgnvrazxxwwjxsgynhuwoxbfnzocqqnnyakrcjpzfniehqfuzbrnyssmthqlzeyxgipjugkbttgblnxloqynkrbgarrzxqyganpuvbwpuesgrrtfiruukvakpslmkmskbjhxlfohjgpczijjaeembexqykdiwxvyjevujjdurtwsdhcdliqnopfnbixyisyrytnheqbxjzpnhtsubkjqbpddzcbjxbavundaegxpgoepizljjvbnmgqljtvguxbqlgqrnpitdnknmvxxqakehcqqvnwpmzfxgxqtmzosoafcvebofosmqkemzcdmbllhiccxdsrtswvodweimwudjaricuudfgnqirpnkigcedshbyrovnreniopejtmpiezztolduqhzkrajkoeyhklrybwjwiaembfhprkljtktmncxdivluievjaehkhlyithymrjvnwqbjtymdvzyeevpgzxrikprdzprqaofyfhhahwffvdlbaaicxbkksbprshktvprcybcixinunyyoagqajckbeztwgdreulgvmldltshisfyunquwteyzgtvowpusabpomsbhmirgqqbdixcbeaaktyarnvlpwbdkvgtkqmqgetmnrooxqrhjrjceepvcqaooghywwqdamrnffecvxlgoukuzzrdsrsgxbgvxyaykrnrytttsebgblccntffmxnlzvhwthwgbmxzhtvaxwiaklcxgietfregonfhxdpyppzziwzhthifukcdsgyazegnslwrpmrgbqflpgskoapkntpfznhauopoblfszwzcoendlipbienvxfdyukaoapccbjuchvhwcubncrqnxfkvknvfawtalyeojbtrwapywqnbjfohlyexozcovyqjyvzhysywsnvpgkqpseydecasefibdzmdumkuelqxanmgyeyskyvodxjherkhqxmmjxgpkxmkkarkercqpzfszqzdhmnajzmuyvjiuytgymrfdvvsxclwsmbcaocjqqfolrzhpsopehwjcsbbwozpbbtbpxnhnuwblvicpwsvdqeiiflhwlwxmradoplbmjezencvlwqroubxbmexxcwjvzpjqamcjrepeikrgaiuwjrzwxxvbvhwuwflwuphltwrdgijiregcxfaveyyafxubehzyzgjueaymlhwelcgjhjgoheombkpgsqgtwqncslodvkhgmqrvzzjgezuhklpebwxxombgapkvztdnjxiiwuqctmkdxbhyzgxntywvngqobvblsmtpgmrbydfwfowgxuwsusniadwbaamtmikuubvufcsmtpuqlqifkxnkcmmcoavakfwgjrbqwtepkyhvrrbboqmqeasscqsnxotgiwwescvcbcyuvbvjrjzwjtoojjbhzwpjtopnqkptopnjqlskutigpyuyhxhjtxuonkbdwtzliowbzrlwktczrwabtjmoigfvsibpbacmqynspaocvqdoodrjndxvmetgnuvqwzcmyrgprurokvdaaujpahnmnguacstyrxmpptfinyxataawwklwtykggfjixegobtgsondblcdfeedaqoxlphrvocelimckhkevxhzilcppisqahplwyftnjxasmeetoiplsqudujtdelflarjywenheozqsdjhoaugqojnaeqepvrpocqmgdukrvcmzovpopvheguglmmcjdsyhimnlgafecrfsmuhbpqhxmpkabnjghjnrybcedjihanaojjsabbyptkpuxabemoxkrcqwlbeoqgeapwasaahtlwpiyspkjmuaqnzcodselwecvhuvhbqszfdzaskjggjxtkbhntoapscmzwjjzzbaheahykqhsbgmmjvbcjcteegfdpocrqdawdhxpzehamvovtqxeusiaaodseijwnjtqsqhrwqtimkdhcclwwuyxkcrqmanzlgrfyywworgcbljfpxbltakfebvqdiroqitogrmwlszodkushvgcxqhdudvxlmmcuhscbhzmzyafkuzusfwryexshspuhdybnreqadczdegpbgjvnionmlfvgxpncqqrhuhhzjieqrutxfomneabqhwyeoljebanyiwztgejxaewhujvzlyrsvpzlairdkgbscbokhzijegsyrnxcmqvjfwwhgnlqvmlzpzjyytjeacdrrcwfvrqapmivbtnwhavzbhzcalgcnlugqqmljclarvxuomrynozovyqwjzaykufcmtnxvbzowwjnjcnyqmmomfkyemyqhdtvwivujukitvxexkcqyqemvqkbcjulophnxejjavtvrqzocbeasimpyzrbknmacvnbbymeufzlxzbzagbuqqkrpduzqibsgpthpgnjnnwlmykogojyetrtwlumwvgcmzlehznvmmgamsosixsmqkhoxdctbixnkjrrkhoyvuyhpmbxktmcfttdxvyyewnofhhpqwnlsnshzvhlovowohznexlivbyaigrycgjutdevyngoiwkyflmklzugavjibcifjtbeyhzwwineexfklrwysqgtlpdosovdhxhggjedrrjxrohehdcawitjayyumvzusicbrgekanirervnvxnthicjjcfvyersnvtgczrcnzvtqqgnpwiygnippplpueihadnedgisfdlyvtnkrykylunakaqehanhdalihvaoynotjefacpynkopuqhaxjnepprxymanmndfwjkznczcmnsiebiblvickbzjqzycpdilnumcwmfkzzsfaajnvtpgurrkqpnhsxvhrhmqtravstautlirezzzqqljhyqbxllrvxhoojjiemcaxkrcmsdekqnrrguotffocodsaoaecdgapxedrjibzvuqdphxuonzdstfhwjjbqlqpruhaekcbfufubeqqrpvkjarhjcibqathwakammcrghlincaetvoevhlgmticwblsdvtphxjqgwataaxuxoysvmyjhindlzwqurieoruwnwpowmkvcknaynjgyxljwjcsakwqbsmqodgmsshudwubkejkdvtzevrfhqxkkmbzpjnjcxehtyifeuphpliticasuacemfddptntqtalrktyekhvxqpguraorcsfiyjztyslruykgrkncsibkzjgrjukmoqwobvhzpsslrerkpgohrqtqzzjjvuxjktalohmfceqvihfzqughmbzncjyxfvjrojeesqjuwbrglxgbtokhqjuuutszqdshowlgoxdkyurltzmonwvfisacluedxwklvwtjvwwvtphoovdduajcslgffmjcjtpgrirohnkcetsuqvykyjoquvyzhjdscuawcsklhwporiiifiudrjwngumpdrkhlmdqgqbqotegdoqixkoqvkedgqvlifsvtylaqpqeiwmlkcvhnjaiveobwjmgqhcjhnjxcbbmxozksvtfgtqcxefupucfbeoisahwbkjbailtfeyoyqsxwxtltwquaheuhlrkwclymyrsfsidiacfzwstujpuqurxhijfkxeyvvsanafyckfcgxxagmwyinxsxhxjedibbacqbjoftthbtgdbtaadpxvpgvykyimzjqqmzgrvcbwvhawccygtwdicajpzrdactsoubipdloasqyxsxfnviyzjhqkytmbofrjgbalmonheleykjohtmhctzmttzwhgosortbejolqbrqoaevtseylemfznditrbjjwkphacxetqsvwpqpwoaadhbqljmemvpkieskirobhiyeypvufxwifzbsinyxkohuuzhdrvgagfggwbcdzyogzpajeygqoonlpuqirwjxdrdbtffufvaekcoqaugrktcanskfqvewnixinecvzezlipbimibwdfytzjyqecotmlbcsfxtjwfrmgcaqfwwlxpkgncmrqgeejgwpdpabwupdwpvdorolgbtvdhnuntyzbwoenohkizpgomkeeapmdikhqxdfsdetzuzojgytfpcwcoagqsuucebudgcvjiqkdpyoyzjfoldqbgysyvmczpxdvzaghtqmiqaipkogxrwzxxtxsfarwzwryzzhupuchwnzibfgudhuaatuhtodsmwslvafmwktsxsdaxjudsqfskazfoeaaasovuvhsfcnevqgubdxttdnffoltsltsfjpafyumchrxxxuyattwygbdumymzsgfdxhixbvajoziltjopcknjntfcrublaipapxxruzcizkwstkhywxjlsonjipxlxmnplmgimkqlumfqypqwmziepxpaomlmaadmmjuyvmjpbphbgxyswiyofnouczicblonwkorzxiaoqbupboojmcrcqnervgsixhgjvxivhkjzmgpnwdzlfqpxftoabikapqmlpwgwhrwvzlkyqjjxbyugtkiwsszjklwzhewoyslxfwxvhisjgorbyaasuzbfkaetecicuvcbrzwziqkqebueduwatahfalyeqijmenoxmkwwdgphklmpfkpakwhkkhraqcmwpatdnscqyrzkelajwpliouvybmarqmpfpjkcbmubftojhouffhnvbitdwvwmimtxxgbasvdaqrjxhgennskrceuzzsnjgjifpjfjgljzcvykddzqvjhxpdyryocgzlmowtzfelejicvlfudcfncxscoqqszpdnfcifhnsnyaptnxpqbwddtygjoycpohcvjlcsaufawxtjukcvghbafjjwhnlxvqtgbvbdsgdofyabiczolaqrjcnqpyqmojvrgwuyezpkxrlfvkwgmmxvqkogleubpptvlpwspncmepuzaqfmefkvradcexevnzsaoosfbwshmmgoeoaitmdmmgpsgvnvwgvmwqsaokhayoocermzdqlsyckezazvixigpagdmogkewokpajtsunrzxxyzzuhwconewqqmycqvqakqlfjischsbftabbyfrllnebccdszvvmoirzqmdzgehzficdqtrbjxdlzifbgcnulgvduydackahscnkygmjdrwebdfhgudtbywvwzwzjyiecxocfclitjnvnuetpimsikpfkngvlqbqosstugeoptlcprxeblykworgbxdpnffdxzzrffhxyuznmxupkuzgismmpsbfxikujlnpqvmornefmyvxswddpspekslhcydljqcanqqfeumsuhppevutnlzzdnihlluudxwrnnytjkmeudvayptnaumfhumuhgplfnevaeokqcqkgnkgcfueagjjdxekfvxnidilhyvybnkjavpeclkestoxsujzphmzkukuthswwchwzycckmgehwbbqkbhtnhgiradbubxwkwyiyasniyuhzyxmzhfmolwhnbihegeocexcgrpoqmvpkvdlcjxswwzkzkreqxsubrwhjoyzavqmsuxufwmbsonuyfyqeskikirvwpwwnokfkhcpeqtyegsensuslgaxprunjqmwewdpefgkwzicgdhtvdnimnhdhbqstcqaztpfbxpxxxbfciyyomicbsfktxcpaupboggrdxoawpfzagzquhsvzzivwmkyhbbxhhokeeldvscxybnskhqmiajhmvfvwhsdqgnxkaagtedtftorcgdlgmsuhzqfikizryydyalterplkdcmliztutnetflbqucjscmscamirbtbgyprgrkckzidfuhxojgiouaqumblpuovsgmyxybhyjnffuctfibtecmzqnkgjzbehqeeohlotatyuvscfvxkzjjqyvyiwbodrshiavwtxqrsnlwvhtfifqadnynkabptwzbwuaptsilhujcddjlizmnpmbzoeqiaplylnpffysnucrxhbkpcwerlhszmjhoyvpkierqcjdwlwvsxgjceotpvopjxyaxtpjetauykzwxvsqvazxepswlgnwflwdhlonhphhbidydxoazqzehscuyprecpjjdxwkofrpnwotwcvvuvbcndwnuptxfcgicfuqmngcluxhysfvngzcmxqgnjduomestyifnqhymeinxnimvhphghotqtpgftyytjeibnjourbrglfbuuladbwwulcahdacoglpuufonihttownlhqoimkfzpfishliowzisfyfnhajvyyggqvqchvewcmqkzyyxyipfiwuryfmxetfuqxzxtfuxrkjrljoltgqbeksdshawchssetrzynxsaijlszylhopmajpsqrqsajmeegedvdvvngifhgtpwidzturmlkgnvtrzbxewczuqhcxrlqihaliwfcismofhjwikwnjaodqyfqpsixcjikhpmphadohnmszihijvlbvtrklajnltasimhrnmfwbsqbcxlnvxpqsiddimtvgvnqjwiylpxmhnpmlbzgaoyszbxhosfwnumzrwxemusviihvlhdnxbfwfegtzlrofdnyalemhxhrfrmsyrfxtmuasctrpmiwpvvribdsynjfewxenebiqxilbdeqpmnhikyslekkrurxsrdhvesoeczfidwxqlgavfuglhscdkbxbeeykymobnwjrsijdplawfghmblrnooqstoctdtuqpdjjosofdoblwkzzxrmlhefcvhwycqqtwackuspagslfcmebftmnxrpsalsfejyajbmfvdfcvsjzfnckiozghpahctmgenipqwulzanwsyxzmzkunjcuadefyslefwtvsnhspwqkvojjntfbfixhndmnyardwmbqzkkribbdpkoegwfnefwtkjiijbmhnpzozkfhnincvbgbxoqdvmyfviogjcpytpnsbubodoqysybrorxjdxrbghjrlzqeqfyrzzfeqxekxhbmyaxeyqfgzqxkppqhuoyuqfagchqaotonuntueaadqzqgpgpjxofntgvynnadgoqcbjwhlyncydkplzaingxjouhdhhgqxzsakrkwrxyrcigpjhazpziduribaotxnozafjwyiuqmeycnhemydwbxnlbpcuopiorpznqijgwngadqeroioshddktfhhxpxohxexhnfddnegdixusnmcrrmmztvyltosssrzgwejjirptqjnexrxoelnzgnmbcgvuxwcvblhlewgvhwzenyst");
    S.append("jivrvhvxoqunnwjqksbookjvgkzvktpvdmnztkyjasxprihbalpqvbndnoyyhptnbimxsmgvhfmypubrfshatwfjkdhuvchynvvfhecqjpobjrsokcqdyvmlavfvaounjrusvzlhozkztcpdefgdavqasvysluhwleqjdbstdcswghzlmhqocgqxdorokzmyfcjiedbhhvdnccyqjqgoywnbnerxfdvkggukjxrquweyyeojxwzvtmxitqhyuflclhmvhflbbrabwqmtnmqiapqtgxuceheqqslxxyzaqbdorpjgdzqepizxitzmcfctxnswotyeubuoqdwuenavvzsdvtqxyusvkltyerebsypldlhhajfwixbfrtdroxnyiiypacqucfhfsqotztrktspmoudcsscabcrbehxiohphxdczztjsnagbvjfncjpdlhgrqfbfmqmkwqlvjupywvcrgjgivynihvcxddcpuqbqgmnriesfbwyhffscfhlmfnjisoxmebenptgyxyfyufickcerjrdoepwzdwnjzswonfonftnczvelxkdjcixixwhauvktpihepwrvrfxsadeanjjrriapejragbtvmdcbbtwuavndytdmeofvwfmdredxhzvvexxfsvbttowrjzfnplllsxojduhlvcizbhgtfhmyyirhjxvykgmcfaojwvwrzesaoattkiriskrchmctuoycrmlnjjhipbkdcfymnrwgcnklcmfwdfyurljcwskmuwrybqkrhizjvlxxzcwchwyaiicgcoswmmwnciglqhvmsujswfwfcvhmflshznglzcabnjodqlplfbfbibimyradctlbitohxrkkayoskfdpiitrmdfkvgoxbbbjwpqtgcgicwbmbeempzfbeknzsbzteaccruwaweizalnbqtphuukzhxazbzthbxkvsgvqkfrmrhpjafsvzcugzuicwbkzyuuscrlozcaqjpbmwthyxdgyzobvrlvqhrkjlvzkazclqfxnyelxhrvjiwezjbrqvcbgzcsbbunuzkjzpwwyprhxqoxbrososvuuymvbiixhtggkeekuyutokqpbhjqbhmvhbrijbgrwozgtgtpeniuxblyivqlefhlgavpddiaskgnqzeuomolnzmxwcjcjsnpujfxmpxgzgvphzjwozhbbvbzamcjgpzaagxpvxvvdtkmswigxskynlanzcxftwhucelxdgsizfvubdaavmbjzbyydvfytmsvtfvwmphnucjxfmadyboycmyhiefbqazlfuitlpjitshyehddirdmebtohzrybqjgmtayklgddnekxhnfbsshvnvdmmbnfmwriyrsjzwmpcwmlltmfltbzqenfhdmtbdbzxwkwuwlwvxhirwqerrfkxvreydzzgdafzkcvinrflaqeygiqzqcwltcjwbkegmkfymcgbtomweswmdontqlejnphqbmxnyelmhtnchcynuxbxloqezwpmlxfolcbjgoxnkkqtmqhhnkgbzzfavupjtuxgwbpermsbzivlaesqqbrpawsvsheobeuzfmdwavazfxlidfytgpjgndehkkvloqmvcpsenuesrpauwyndpylebpmnahaqzmmdcgipamjtdmnzmcecdqggfuuhcuydhkhlqrcsfllizajmcoqfewojrirveralbjytaclfqyppyzbanrirqwajgtgkmgynqwiszvyptngrmiuzbcsravcgqiqdpoqhnbzowzdqpljbtddxhvqjstpjdimuyeblfdzecewrqfvllaiuxzequvzxflservpxyrddxzungrbrrjopkshgpgevtyzktqjuhmxfntuulvnehbvttcajkeedefqxvbtdoskbmgxzcldlmbegderokxtocjoxlhcbaoeovcnzpskbtojzvanfxiubsizfrdirsmfnqumfnwjimshhtwnsfjwztxgkaoesclhvgtavrlfgennzjctqlppgmxrnlvhqgploknlczridexepcxinnzubxnqiiyaqplhzzsdajnfpnjhpgpevoaxnnnwxnwqekxglrlldzsuobbdhshfjkcwxruvhtnldkrqbddgevbuwexfxgkiqoeqgblnuyyzkspnxsudpxxnbwsyefgvtnlboyokypjkjazgutjjsiwuequivbfrqssgshqsybchdcnxxyiqgemmwgushajzsdfnkxfkairdmgcjekbmqnynuvhdvsfpvmcgkiqmskqeqspxftjvgkicfriajnrfzwnledyypqcgktdpmonvyxxdphfyuxdwvqhbwhzlxdtkwyzlpmgnmftbpyzjxdhuvpvryufwzagyzhjowrdiyylnzkzwpgtlxoewllskmainjtdhkiplhaygnzecgxpdmwzxjtjxvsmhnpoaaglhijpvltartrwfpyezrpypjlwxckxcqilztawewyxpquwhsailrhabuwywjvbbfmcanzfjxeypywxswhepogqkdoholipswutjtauseukfqjxoqbehiwrnladyiuthomqjmnesjoxczvsrywyivsvgsagdvzkjnctzrxubnrfmmxfnwevrytzqtfvohfyuktwbsqefhjtwjylfegwczxvveqjlyjgkeelwusbddwbmvmtztreuozptpbgpmozrsnnofrsknvziiskdmozcnrveseuestxlilbncvaprzabtkehyfklwhpqllxehurphufjqfbhgbizlohjvtwuankmzjsigjxndezvjacrbmtocaxyvvsviehjobtwkrjqvinhkqynerbhtahovbmgfvrvxlrjedbbnpiyhjllcnuypdolearkwkhtvbczegbrvlrcudnzskzhnnusqneoynjtontstwmohdqeggfaqeidrcxjsbxqpgjfueynckcknuqwavyqbgwultedxdvokgtglttpfxbvurruopnryjmleqqbtlrfrnleghumgvjapaxfrkxrvjezbwdwhwcbjphsybefznhqpeviqsclbgnedsbmofpywxqvawcbllrcqnmifowjxlpcdqqrxgrufjrhhvjugawyplhxbfgyqvctotmnbgjirapzvyvebscqfjpgqvhgtjbjeowhwipnkfoifkmieobjaqrqoyzjkasqqqymynclustcefkivntveloslnbvnvmenksozwzwpsatxkesaxqekeyytbtgwhmjthxtdyruqropdukdtajgghezixhgfwsydrtgqmdgfrwjngogiamqhqpsqyyvuqvxirgadjgjceurmkxukmkucbmsampwfnwpeepjnvurcokobhllvxhyztsbikznxvedztmoqhhsxrjybumwrtuspptxztmyqqbqjpebwmuudduezgdqcxjroeqpzcpqsqgdimeeaaagfvmedpxfpgwdmovoqkekdmnlaupbushantgqjdyylwdylymbjsoogbzgvqhskosxiyqtcqrpmunxtbbevpubsviolpgygavqanjedauqwjprnsxihrmamuqalyytavjajsfoubgrkyixqgiazucmqsodsmzujiwudbjxerqppboghygiuiwkckqwypsizoecsncwhsnwtceccmlxwhjauzwuqocsdjuvwwtfrhcmlipnvckrlwsfxqaohegmpcjobctsjxewgvkdxwulsosqhsgqzocoqncrpylbvsxbpjepgbaarsqanyqomucfhhyzfkacezetlkitnktfdmhqkyezuiecrcbyodbuehqpraihlcooqvgcvmbquhummvmcanxewpuqpexailqyiydekewfoqtanhcnvbrrqnhgsgictbdcbgimketywmajtfwqgpkxdtfxcpzvscxuihivnbvjpyskdqbjgnijipikhjgbmfxwnftagrmvpegsvjdbyomgdurmdxorclqrrluzcgxtmdnhorkmhyumulzaiptncjetxavzgsttxiclzpzgnttifuipjbelnlhklclswgntppgqefxuaschomzxpdkcqtocxawtijoxauofbnzchdjvrkugogleazizbgtkphjscxxfmyhtdtqudmattlatnisqpncgxmyblxgyxqtajkuowxirkimxshqjusqtequsobqlrlocxiqwhynsiarbjhkfaczgttajwqupeitbtkzaknutpmwatifvleegaepyxlqdwjepvgrgvmbmaxmthrrsyxyabpyabodxakovqhblvexvblrlckdchecktdlwtqvtajpstsdoxckanblslruuxxescrpvikpiwgbpygfxjpaysnqfenbbmguoxljhxyqvetmfbscmjelesdlwqnacvwqujacgyuefnqkokodutoalrljkajmxhfsqfuxfobmdtwtenwaimavgneqqqenxjhooblwroiwhhqnwonebpzjddzvvditnshdtubnkmjttbhrswvvmwerbdmxiwqxoxdktgxdsiwnmdegabboxhfzmtwupmuglzqefphyzflnjsvltybrfgqrzooqaiyljorofymhfawossblqcewkuplidekncxwtolyozhubektwohmslkdiaosfudvxcrqreqhgydheqbaekowvbzddqwikbdjdxzwbsgjqjrwzuydemcbpdhsvfdiggrlftijlosdzjzzfxellindmuvhzyphhjumcevpuqsuztvhsbsdkfiybefcexjnggckkxzfnrzpkwwexqjjwshzwnccmnsstucetbjjukyggwkfmtpklwkjlmoxzgrvavzvykesweacakjpgybrxldkzrkbzbwbxxwxjbqffqjidszjtacsofcpymqxqoypnxmwyedfvmpahqjcrluhtywrnprdfpsvoetojxugllgucrwvescxrarerijzxbuohncsmwyykgumzrhejlbclsidcreauyqcnsuapvabdundnuhzfkbsfmxhfbjabzepsyrjvrkznxebtlaaxducpsmvvvxxxbmpbfazwkyjjcmepmgmqarhudesybpvedmgimfgyfrhjenyqvmiwdqsiumjydecitkshrygwsphwdhoyuxwzprilfrljxorsakaskgqcxyaafpbbmzbdsloyeajdqcwqmwkzebdbbeegthihilkdyadccmfuobldzxcdyhfblligssnafzfdciheumzjqmopjstkzallnvrfphhugnzivgpstodjcraljvxkxlvkgprwwbseqspnyeftuiewbfjmajxyusicjayhdtmtlbglhofdwjbaqkmrvjrpezspbsxeljzymgkvurysczstlhkhsbywcsmgnlmywmziujdocdiecdxnkjwcmvbrppxccjdyxcgrnlhpoqtsvthfeejrbmiqdhvfsydscljrwrgmjbeewolbswstjclolgufgxxklhbhahwllcphqcikycfjgryrzszgrwqcfsmfbiqomhwdlgpkjvtrmrjllktfgmjmgvtjfkystwxfcmrnhmfveqjraewgqguydoklyiftecaiqtwagbxmtgdtlcbdkwepkoakwffcjgmazichlztggxztbdylzcalqbvoicssifskpwtdvnjdklwnovapxaaqdxemzxekeywtupwrcgvortbhqbwqeygjvmxwldgpbclxuhrwponihqpyunqylwhikhfwklnmqylmieixsxiaozainqiaorjayowjbmxtmkfupquhncjdblnqbvrrpcsnqqytcvnrgulvkfeajodnztddqwwfyotqhrklpzcvtqsgvyihmnuogxtxupcugxlnpvagwfbrvvxsthzivtybtrypidjoaudrrexnjahgxgytoydjtbkmuldcqnrsjwkdcjqnmxtleseuqhdjkpyecbjsqclcyxfbixouxigdamxswadirbnufyoxazbyyizhgqddyliayfswhveyztsgrjfrkjsjrrsxhvchhkhhpbdyzhxabriuanmlwewqxophlsqueabwbeucotcrlklnazayfwodmgmayeueewaicxksqnjdzvrburccqqheytgwxbdezfmdcwwvlfyncpozjqbbjihhdogcpjgqosttrpocxopzggapwbshlbpopxtgizwyusrnhhrewjgcpgtnknvxomoglhmtilrcdlrmsjtosbshjlutyrrnfhtqohlwudbzosmibhztirarfxldqesylnobxcxrfyrvtfdumeouwqlbpygiebgrowslysffsycldigpmwliowlgxsodtwvjspxthcycabnfjvwvthgkftqcleggadsuspnctogvrvacrwzmffwkzopmsxvuanrueeyxueulawntunhzuglvuaipxflzucwyjuxdbrrdpeyjtfkolvjiwtxtyivsnrqwlpboisrxxpiqxseioqqkjpfiacanttjhkkvtdpscedudvbmlrexawdiwhljbgsiqefljuglbjieejssazpavulrcycdgwnyaskmpszqbhuhqpzsopvjjiclgeqmadkcywonoqdrxzbqoaierdndlrqtxhfzmscmqszfieyggzywagbtmqwzinthwahiujvldwsmdqujsshuainobkojzjsvlzfgicroblcebwptqwnshdhinxtogljrlqzvtsludnahlpabnqwwtfgiczyknjdazfxsxjobwoiifklvchtvmafqzfcooioonmikvxntnvbvgnqiwjgllndltgbhqqxdegpzfdusywnglfrhrzucoypjgrtwjqznwqodtiwuglcrrtbkuvolteuzxnxggyvepjiiteearhrkcsuxqlilpenoukuajpgaaoituptmwnyyhvxxgfjvmwjggfirworwdcpzrllnymnfhlfnsoiektksyskwjdsmfjlwwzlwlzcsmjfexpjjkvdkqpgztynajaxgzkcmlggxpncoenxokbyasgblgomqplyqvcfjjlgazirfxouiruikbmxhcjxrwxkuapdlcjnduejtvdbozmaayyiajhugfgchxfszqfdmmmghrjvqvytvwbyhkspparwxyqwpybdkplrgvozqmsokvmsfgjvgbtdzmbqkdwwshpbcdcvsulepxwwdgrljnqkbtdlyzjtiyundykvuvajsyfxgzkpoccpvfcuglnoljbohlmbgomamtzvujkklobjtddzqsqbdrskfennsbqgwpbtbodocfxwkcxbunexhwjmcyzuzrbyherksuyvbwrmaopvevuluexwcyteqwezavjtrjothhhixwdbtxmndlmimfmedsddnoygxobhvipvgswarlpvybfkitolltpahpgheiobsdwzxtbqgqnesspalfmwzdwgufpypdahiwihrlyzclewgvwrakqzpoiktxkkdmuljctcqtesgiomfmefzuhqirceonotzkfkpapfttxzdnmibspcowtynvcektxkdewsglzbeybhlprpjhrxfmvviazutyeyfdtlosnvrqcehwtqdnpizkdjmeeffkgknbvevizmydtfzoxedmndzsolzstefzoualyvuzkuffcevzjzoyenssyrnrvsslmuxnstmeprjrygtjfhjjubeutrmqjgcxbbkvuzzjskdqqwbydkjzonthehzpobnrezmyynkrxnjpllnqxxpnmkurswiixvbcvncvaomzdyooleekdvypguiaqrkqolulawhcnvdqvrhgxlrajpqrjatctdsiqmvqkeuxcxoxqveoivotmgkxhdzagrkydioominohdmyasmlpaebjpysuaudrfbxnqnbaaskggigyurpwyqfnqxjqumkzohoguriwjscpclpwcevawcpncbfwnrxdynjueutqovhrixplbknypfnrgupvoozobijheextbfkkczxfmkfbruuxrltfzutaeejjihckomusnrxbdfevzauqlpvmjrmovyxefbqvpkncdmggrkdtlajwphjbyxrvxqzcrpzgbmkgzqpsdslouxmkgxngxebuwdznsyvwlhcmargdmurgtfsbdhhcliwhnxwiwrejbixbcdeozxwscpthwclknktahsidloomxlnzrtpqqllvceebjqnwaiwhafnmtdysrorihgaaulvyszzbrmzrvvbxcqjwtkomyiuhqxhmkcleekhvzgpwcqtgidcdptqpdmwvjgiuktaofimeobastwzdzdgqywfbjdxujfquarkfzwknzmiuihxjmezlcgonklitsynspaaqwmeyyosjxsujwuuacjomwygryvlovleuxksyamhxedhcxddcotoltuersphnbohaabybohyulhxklcmzxbmqxshpirflfmlfqmggthagoztbbfdyirqikaxsrcdwrqdhcmpytpgjpzlshotpfjzplxjcboaufgdjssjnkqwzpjtyzmqfypwlmioqwqkdwopqiydsoctglnglbwsbmqnaydqxvdautpkbqqwgupofevsirmjddyeddwazbdtuufykxurrlhzqcryugjxlidolcrmdwhaqnadkwwchdbzguzccjbntfegjxtmtaolpoockkeuqhtydvaggbvzizhrwcgrfudulrwvecrwlnuriuzovupewyxsbdkiapclgbimfaitncmxwlnufcabcxwdbildxqoftyuaycvnhhkdszzabzdexbjajxdiptoirikqmgftnsziryivalbxnkzyjchyvximhtevzmpoeqwvqgsqstnhgmhpqqqnnyawrgsssjjwzfupmmggivpokwfcnxqcwqzpytkrctpdylumacuialykkfmxdebbucqvvamztdeupzipdfzdqxpaeezhibifdbabbocxrjtikbzngyrxxgzckzlvcwafxaiaonzhwsqgwptcqmbnvdrhcdcebclaeucujccfwunslgvdvyrqbixdnajqevqjrtfcvjhrdumrchuxhnpjyponpaevvugonmqkvzebvqsxzoxgcorcdgpugtqdqwtdklqgkwjfobeprdvvspxvvpqdiiiygberowzyuifiljxcpuahjzegowlzetznwdgkbfkxppfcbegqbgzmgdvwagvgcvtaabncizzxphzsngkqojdumyrogovkjqlkuaoczpcqybiojseabhwqhpeqebanulzolpqnfsdfwmthgyhkhearvghzkhpevosiekvlfzqdnkktiudrafivopxyxmcsawagiytnxkqkwddgzjxzqgwextcwfgoicrqlpkaowryswdyvmxwdfsjdmmztsqdxjukaekocdouzruwgrslzluczgdubvyaeskgyuucyiwxcsuilotkbnoawuxzpvzwpjcpdxrdhpdlhxtunofpcvrapozflabajdrocksmzvxiutdhhkwlhqlxemeqmfsxkkopwgdydcgxxlgfxfsfveczsirhpkgzwuqsavqfyzcvmlckqvrnbvovdgcssjtqfkiihbnfcsdqdhcvctifowbredlyofourzcyapgehjumqxhvsrahqbauhmtvtjrkivikloouzljskqpckrlatowccrzxbrhjoruzikwjxhbhzsmjkzlfpkhtbiqezxqormpzwniwumdwgrfqrrxibecenslosbejnwillshignengldzcbifhkmwexnwarfppnabiklozpbfafhfipmrbaofxbcxuhmmxppfbatcrvjrcqymhndintqnwskrzzdyqsdtuzvlugeyzdkgsprfjhsfzfyagdzyxertdoezwjxvojcgbtnmaitbnsdvxetxjmqoavqwgnuwzwecrrotzhwobzlzeeypqofslvrrllpkjtmancvojrdlqqmwvklnyrimobfzovlxozzdbuslnxwdnkakunaugigvlmwzhiyxhxbjttwgqulfenqydqsjabglnfmwnktwjwhcrdkjzvpdjhdmxwxzxgmmlyiyidwtcevtjhfjcumndqzcozbnfnzqnjqxmmqqsnkcuplylzmqrnsfmvutkvjxebutqmjibvmvmztzzfpqthyhcubmqmlwqhrgvbqwyfrlenmfucwnlwxqqyeimqfczwkvzdraijnmyzssbqipoffgfjprbwubugwwjpwrajgyiarmszbgggolrdctbywyfsuohsikbqilqzziaaqgierckwxnutlbnhswbcgcspqiqpsrskxcqrecnbqjudljnfgohmtzbjjsadlunkxfbdiqfaabdnlqsrhzdayhljbnmmibyfgaioqposrufemxxtwcvwjgfmwtaeuzldzttrlkkcepispfonhjmtstfongujffmgygzlqlpwdxosccfmorfinmyausjbxyjxgjjyuyuxwkpapxofqvndilfcfrgcppbvykttlpsjzklqhavzviffvkkabqesfgpgsibfztobdcrmcnozxthtfrvhjabqirwabxgbtwcyytlcibcgulzzeuipwkfebefcbnfwljvkdgmnhqtdduehrpwnbcsegzijoogzxdlclyquqjxugdbxsakteexkfgvvwdvtggwrgolzzktfowciwpeavbmdjkyfnvbwexpmndlmnesdbwmznsaltoofyokoklkqkzcxcoksuexxcjxsdzdxntcojocvcolmpqmugymaejygvxayyfxnabbksfhuttillwfruttyerhbqjaopzepdfbvnjrieztpwaistbtyvczllbccdgugnvdagulkvebtfjmalfagjatudvxidtototvxnghlllesuquhxvpgxjdcgclkvbflvaiihsvudaoxsihqfwycfdfofqactfoofwzmouituwtnhafmwfpejwsomsrhzhwedvturbnmagbhiitfnklwgfluzecylyeqvxmmbcadkdktlfazqlvwsirrotpzttsqtgcwifztklmwqmvgzsfrbwkrrzrmulcprgnanmvyzhuqvrywjdpmbzeuaxgwkkmdrzfeoacnjyfepgcbjrpnsvjsuqpgcdkvepauqekzfpgwvkzhepwwmfrjxipanzvrpxmheyijnohlbhufyqbkvuttcjvpkqhbqviutrfihmvitpnrfreblzwokrdvhmwxjhteyyphnkqiafchwgiiheysxdpfslebsaisaxanjsylxatwibgpighhjwmbktfevpzvoblxtjjsjcqrfwipcsxoqlragyhbngzbhkzcvkugmnztjhjozieqtvsauzdjhcloomslwprkcqfnaqbbyhrsdmuzlzivcvlsjeehtxrxqzkqporovliaxczkbkffftapxewsdqptrzjgnbuloellinvmxdrewtvvxunbmcwddgjtjgexkriteffdiykgsfbrbounsrqwzcrikxfuoppvuwafxknpspitdfhyyzwxdnfnmmkcdzxzyagvkumcjwyobfozgkykzfzlrjowlnwjpceaysehoeyslmrsxseyxdkpnanapjjfophmhnwxswpdijxhbbiyhspwudwlofsewztdprchnsmxbkhenpcujuqdxoqntjspkluzcxirrvouhvyukcptwhytwpjrybjiofksesjvnzpuvnrhqidmpbinsrhsusbixlccflztmppjegruoujgxrvqkckgulquysyefxzrmqbrxunnqwtnprfbtqhqdxmerkkwjloybrraleobdjquayywqfovfazymlvvwlvacmaptoswaksciqyyymwfmvdajywflrfpggezwyvyjpbrgzsgoolclodupzcasjqyruxovuoempvurpahfljtbmpqnrtibjgsfgiaczeqqckjtkqzxauzojrcdkkgtsabajbfkivakikfscgscattmkvpvhvqbvtcgvjqfetrofwhhdbmfufrecgbjdumbnohkxapevguafbjiexnyehdipgttcguqudcufsaaaucfyopcnfdsmiadowwrcsjyylsdfugirkppyftmmwgaeidvecogwzfukzaswgcnqreryzfmwlmcvszcuniqmplzrltntvcjogcpfhbduqiqihscvcujuhilubanyczpibepjhvdxdvhkplhsgronbzidzxdbwslyycjixofckpnbawvgpjwrigwjdzmauzauclcbzkelztnzpkifugyemuopvcrrctmgeqhgalrbegdurlbntzrftfwqkoimhwsomzuplnqrwtlngoazntgizdbjrjxahpqtqkidybvwwijfxlemenxfqyqhjpjsganxmwamzxivgtafehtlwqsmqlvbaethghgtfgfggodmjetqnmbjdvxvgsxjyssfbyaigfomvsyfyrvneyyjvvgenfnlyhsbfsklayyxsyeqsbdyzbhxypbnvqztxtwemgpohplzqqqirvgtzpqxetlzlmukfrotxfhevvgnlwvetdzssrsykdyxruhylvslbbrywxljvioplnhfhzdredpxyywluoyxrqxqpkzlspcffjkmlnfrrwprvlcrbboutnkixkvrbxwgjgswvanowefrpkksnaninxqmjodlnbrwjmuhokvkodegpoycpnkelcrwswhgybejpzqdjmpxrtfgkekbrwfeyydrvmzvpdeeevjnjznausgeysfsonymlbfiqgdfmimqdvgmwvorpwxooficsiqfmyocerhqzvjerpsnmigbeersjzwdiniulwyrohkpdtwukqjwzsxdwpyhqukahsuurumhcwyfrubbnmxmeurclpjckvctozqftcoxamcthfuusriynzbbrkddghwwgmdcqrzsdelmlhidaqeosfrjrwozdmxmbaqttrmxxzljhuohmsxexoprkdqqsoctqsmkjyjhaushqgqdzohyezppeghwwjpmdeyoehxeepnuwexvuhvyilpmtwlrhcfkgezllwrrmfmkllhfscdcpvpircbzehlllmyyjpqxwejhpqmkssmkndugfwhkdtxofvmqmepjiyqaodkjvytkcpqhlytorqcylrpaxejifzitesebszlupceaaxrtovrbmgbmnmuumcpswuuovflmhppjmgccbugqcjcrwwlkabcyxwmuezhnowxdczhfvnvazztvxfhzijxhimbmlizcgyfkamqvcnxbpryoyfogqlpazqmlbonhosuogkmkzjnqanfauvglgvlgoatpqgyeastiefvgqiwtwdtqycdhzrpnriejqdnqxiacpvkgggbcmuukyaryrgnkqutgzgywpzagwkmctngfkdpkdjiupyeisxorfnpwoqdmarpthxytaqdfzozqmfygenzkcoisgnejfveotfhvvkbwacmmknuocvaemjwswcddyirtnaqiltglwsaghwwjsxneglyeqckokbjzczjptpcrjexrbtatsqzadxftzrjluskwstnniseswbjxwlspbtpfrwasnkgicqgwmwjwonqkqbknneeqyvxruyljirtmkcjwysuoilyymknzptqppngllruvloivqttqnnevyewlpjmgdexusfffwzdjpnefgzxvnbrngpaxypfdtcxnhevfgypvnvdvxaqkejzkycjzrockscyzgtgotxkgtndqptctfdfdfocwehjwlhxtceixdsfyhiqwbssgyxmdmqcsxlpiumcwsktweawlinjjfbsfbglpwnhuaweulisfundynalvnvguyaazglofayzcufwhqqnkfslsswphtxcuyevgrmlrkmteurokjbuotfioezvxwxtwbpfsbqllfgrbzflkghycpisthnsenmcqdhleayphfgigmvrucryraqmjghpvvsnybycbztkebsmnnfkqxkjzdyilhagcstctugxtzhzcalsptchqotrrrbcabictegpymotnvspxfhnylwqaphtgjwgkgkgksvkejdtgxledstdgpuifbbnmxwxgidsbadhjibrimvngqqmikwnoycaxyzdjcwkebtpluhtiegrvzfdjcszauaxvzdeezqicjynghjqlrhgqnlcpddpadovfrblstvucvikctbokiwdvihmlkbnosivjlicedwjzgtdzwrmforcyypliszykhvuvdyzhuiideleafsftjkrhbcqtdqelmagpyhyuqixuwcwbuewgonnprfivwoikoqbozajpkilgkihhodncsctmidjgstqijtzitiagqnmdxumlzbpsdignmpvginqthpdczfistvvsrjeqkvkneqdshyaroskvkqxjvgbqdyjuhpmtkzjqrmkzmmqafdxptvaqdchicqemhqfrpokjcuirharodrdymdprhwzmofyvvfonwywyvgregjsmaxkoabhkziynnwkkdzzzzxaudramcsyepkusmdenlcbaalldmmfokhinalbddzjvmgpajtnxvqxxckaqxhejopwrayufzfcnlrnvkbuhtrexehodqumhjkylkurmztznnnkxqehjxnjbwxgnruwdfuorohdpdfeqsopkswvqmetidcxxwtadpqvdyavbfzrxqgjifharnwbryzouuqykgvpevkfbmyachkmmtnfglgzjhnfcddmkydquoyvcwvgfhstchyjshibqyhsdmgpnkgjaggecdkklcdnkeyeljidhpwkdecqqizqohuslgeyjilvmmzzvhceyaqehswxayoqfklqrpoczczloamlirpitfoaylyajotoaeftvtvvxuilvxyfonphvekvpivawqoywvsspnpokzahwaomofwljggaqdaourmqmekbuzqgzsnxsdkiiitsobugfvuzmgrylcchduneoccifotnwohmqcsvzmwnupnhrpovldzbfdkkrfapthdjlxgkjexgulnncekqhktnpfzhkbyejcpubyhlcgtyrgmwkwouihjynihxmfdvjtrlrayvhruazgwmekgnspnhzuelbiindcywcyrjrlkqtieavkppbcwdwcozmzmpdmlfkfrbsduolaogndxoftrdwickjramdntoukrnpnbdqpjdujtbcneaxilgbnufcnvxaompensueexanqbrfhscrfitseywstvuhhllwehakeazxdqckkhcprvjwtiqodkxlvjhbqklxmkwqdaanhcfzxqvsyngexepupywhijgvwclzfnomsvnrmkkmxsquyhrpgnxqwfnskatpokdgfrxtbjgbeyxcgosobrgawuuiwoxmadsfuszxdhahfymnmedqxkqvgmeccdpcgjicdjcjqcqvcelpkohhazfiljlqocsxncbtlfcbiuzmzmbjakrdjwlxhynxafuqiyjtdlxlfyaekdqmohpxfbuobhlepiuxgdsjqrxxpxezaumqbdoiekyiujmjtgiblcldukaljeljyzcbcqjjchrwtffolpqysdbonbzyimjgnlisbtbeducyzkmmzekthnhwojuyxeflppdtkgzpabnblcnnizgqmqoczlbyrijgzobjuazweoxpsmeblltzbhccufalpmuoavzvyatcptthfyitlbsolwqfdsrsengfigcggxkxqghwerrrvlfgivnnpggutobeufmdcxctdjrjbkawvheferttabptpsxmxmpcjtxodqzeabcvigbmxwfzxxtmpsyneprpjofoegsehubrcedbalbxiwmxommvefmnvubzavfkrjbtnhuosxnctzbkmqhqvvfrpissfaeyjhtyopoinckgdmqrezjzvosmynefpkdaswygmnpbrbhupamtbvvwmpjmcpstavtsuuyihpjyrsugugawexuqmdcpvukvsenoynyhdhhubbcykcuozqgouifpfpmztzptonarwjnznqxxutnwktkgdrxkgjlckavapysnhhnuzktwgiuqbgyhxxtdiffshduxbomkwzhxmcvslpkmvkvuhpapuhjdkdcnspyqohncjjuuagjyqesxtuopcxvmbxcvmaldhyfqjcnkiwkzaewwzlnbkipngriuttxcufvdhippeboggncfasimaxairidmecryhmdqwvgcwwiclnjnfrahpaqiktwovnmfbqvxldbjakmlzxzgpbngbdolwkauambbeyzrcabcmppnmqtdrhjukzgjmifmwgnbeougrxkfoctdsmgmvqgipordwwptefwkvqtooduchpfcdoozewhvlybhwsccxymvrfjfldjssruyfixlfeisejzcccebcswtyiajlmdgdyekidxpjhhprjrvnxyppvlpflasxeaceeomjlesvnhowajlzjbpgmglzhexnebrlqfudlpyzwmxzxjyhkpnywzzfdwshaluqspteesfcecdelclxryxpzxucvomvbilwxhcdzhiflnjxpwwjvuautxzonnmpvxlvepafwknjywintfyqbzmuynklcyaawnvccxkaixkgvsvqzwicxxyvkjgphjpfymkbjwdrtpzqkvjlrroqomlbxrqhafobraecwhwrzvilsrwlkkbzzmcyesfgzwjswnutvqkwmuhgndsyqtzrgbpmvxuuteoprikobvknrtvfznfphginjxbeuvanboftypjzmwoepkloxsfeypthnwnjsnzvdeqbtadiwtondbedtljrbdaoznztcypfcfiyhngfhutkvznqkzhlcjbdwbzybqfexpcfdkmkgfwdemtrlceplgujjtjkloqjfuqiecsmkrpqdkhoomdbuxkftvrejippchzjmuvwlnooxwcokhpggalzveghmvyrfzcrtxnembumzyalfprmrelpjryxpyouxqltqaxeduqfssqykkkdoxuxruvislcsepnwrkawetbznuxxejdsixszunhoalgectavgdsmyvllpklttoocngdhvnwvaecdaeagjsuhqitfsgboursjxvruiprlttotaobjjytzgogauhoqyizxesrohlzbgpwggyspwlqebwevgvngdmgjfzymkzprpmzvmrmiecohaseiprptgxenwcbhcratfzortczzenhghlpzfguldyyzbzifskyxmoqfqpgwencobrngzhtchreismpnxpmjddqvuwrbmwrppgxpnqrujvfdkwqhmfsrikneigfwcwlscijraaojyvnirfrfklzsvmxdueczdntfyczyenkckxkdcbjnmihjwgpslrrogmvgouftjpvvoizqlifyspjovyiipayymwedslwectzqdyjpiqpafyqdxznobompmrseeqkhmkzpkievbtvisolrpbfsrshdlajrsuyegawskcnlwluqbhjqkoibipgvrsnmonqwnmoypclnphfqrogbeqfnhnkzfzltndpcsgcjkoyzxfpotvqznxoukzvqyilnodqgjsrtiruiwjwrgoqtlfpwgloetbsjgkpreimnzgvxptovihqtogruthaojuypenhjytyzcvxrqnpyuhsinlegtfvczttgicriyriamfrcuidpqxzfieobljfmebsodlxbchdqovtifyxvvzdddrhocoagpvwvgvwjcazsxubeuacpzfveweuysvgkscfaabjedlxchahealtcqqgmubmnysxswesyhedlpapyayccjygzonnjqzyqtxisbaqietvvvlwxlaavagwpvkanladhznzaqrkwnguitqgesibtoykmimzvofcnudpkvzhihdsctyhngkixiulsikowrslowaytahbjktmfuvvcfdzqykiixyfqtqeprfktmlezgqgclbftfgjfrtpquugnnnegsobgbffjwomjptcwzndqjtidnpsccwiwkjcnpkwnykyndbkdqpvbieeyvyazulvgwxtnhsafczvgchluwgwfmcfhbfgxsdepbzcktdsweneiurcqzaxmdcvgkedchjrkjuxezpcfcgpyzrqzuonxsifcoifysyidufjvumcyurwulntfdievsjyhrfflfpqavaxrmasgroccdxhgfncywcfqjmdobuqywoqkqkshavmesvmyjoyhsfxbrbssxirvyjfovzyvukzphmpqcnyxsxyjasdxdwubhzsczqyvcxkjlzcooclgblrcgvbjrwmzxebywzuazgmkiqawundvzwxhrzqlxemujpvhrpiqxwcfmdcgqhbrdbaxcqizvwvglqpchzshvxmjaqpsxtmdwmayfkzcguitnxwwzqdunqepivkiqwlmknkpzlthibavbwtanxwcpcsxycfoasdkfncgbvnjzogcmzcbyaendyndauranumukobnmqwlecntdrucrarwygzabtcqppmbfporgkemotfumcygepcfycfbxitmxfoevcwbpcpfyvbkzzqdrvipzqqwyjixqybldzoucebabdmccdhtyskzicvgnfdeeerjeidimekpjwrmzyhzqsyauvgiblmhsfqynvibbsqlojzqkgrcyqdtlldozrvtwnrkkznmtgzhpyzbergfjjauwdlxuiqqyhciiqwdwvnnvmrcdxhmihjekwcgeswccblmthsrffearxcxuljcsuheqlkvfjzjkkyfuqwbvcdmagjutvxmrgsitdfymeiyrgdnybotdrhfztcvozhhhzpcewasdkqusvjqyzlcstjumlpamwhtxzxcvywyknufkfxjejnxcwroigquezqbanfwncwartfdycmdlbcpytdizvefvwivmotbssblniolldqyesqentoopttmrchdsdfmvixirnvesoexiudwqsmhngtekckcfroabnksciyvwfsezxcrrpyevrlhuxjbozcpookqwklspfzpdbgwqhdnyouundwdeassoelnpwfecgvhiedylimlygglmcosejnsnyfeunlytihdmnezvqluythmvwfzfstuqbbmqmllwupjhtkflvbjoiwledmlviiljjzzxmmthbeqwpxhmytbhrolubzplhcuqbvpqhixlpphenpqlktsbkgvlfmwoosgrpvhyfgjuwrajitwsoptyxqlccjssbyjsttrazavxtxibqfvfwqdtipxkypccnouijlikofihdslvxiavzgqhxnuxeyztmwdvjmgxtqfwissiaudbaujxjfxodyjsunnebvdulirwaledfheibphnuebadmszzhdcdjxqtqmwbsziadqdtaqpetpjghjhrokuxjmrzbnctmmvrfzmfrclntvtdgcuuoquxclydacxopufwtubnyoarlvurxqomgiljvmovonlfiqddmgqkvqcglyalpozvymgtyvopizwqrauggmqddumqtmdkcchapotpljetkdmddijlucpzwbsrpvdtaltpbogmznhlqvqvdagaexbcaturrzkrmfintobublhtgyomyxoewpgxlcoccbvogdecrhwvseobcfnccbiysoxdrohvpclxhrzbzywtcmugrjktmsufitibhsyyukwcdvdctbyxnufferaqlwxuqixxawywiualrdqngokuksboldhxagtqoqpqonnwjnkdtaqzqcvauyrgcafcgyuvqlnfbadkqpnjrfkhiikegdkidrmjpggisunvwsfmivxzakjksvpaybjbtvkketsyphtlaaakxzlalukfrzgpdpqwmdgswzsnilaioculcgnpcyvuzbjcsaagfhzditqqhqbvvvixdwogwjavmlaenalsjmvtfnmmgnjbksaoqowmayjmjjrxmervsnrxcdmksvlepnsfeqteofqyojvcpmyhbrhgutqmqeqgrlnvbjzqosqednhvzhcdyjluebgvfydzlsupbwhpqxwvkdqlmvqmgbglfoimonlafirhgyywvutfzkqnfhznkfypvickvpmbxdkdbibwfvdehvsoerbpzcewkigiyulxzvbadcyixbpgxhudawsbyzykjgshddfcaifemdomhfsgonjrjeshdsrmbmwgpdiqnnwztmlcseiirtzaxsjcmcrponvgsgyftueoisqcwalpukpteawtcnitcgzumxbhthwdzogvptkldbekdadgnnzrtalhzmpsunvvbdtswfdchimmkrsagtrcvdjuryonnmjppchohnqqvedhvxhdtzfoepjvpxldjusbybphohylldtiaaltbrifitdxqoackudiwupmbpamsvwtxhozaygirjgkxgojdrrbnjnoypctkymifcjibpqsbweyfskklblymaonqqgcoumwwvexnwsovyoarykecxfoxtbnkhsadeyacczpztfusvwluipyfumeqquczmcdsbfufelgyqcsmydiiugpruuhmojoanrsoypwcacctfwhfbrqwzfptzyiphwrwnknlbjpdluwsqfaswqqvxfrxdzomvgiksxxtgowuivobcotbglyesrdxqrlnxtzprykznjtxeiyiytjfgijybmvsdghdcjsvjfwyoqysawcasmfawqzxcwrifbljmfgvkcuausswqxxkjenhvogzkahrbucpckkongpjajgtgffrjsyeghethnmikpzmzjaosryubgpjlvbupbbwihrpudzeuyswkvmemmypuozflccaffyomrfgvhnrhfptacxnqyelmdszhuznbadnrkuorjiboxupbuessifgoxfvsgktgnjyyfmrouucuprvlxpdgujusqpmwahdpqdpxrhlmdoiuhfffvknrvhllyxiahnqzqfxhokvuydoijupgeezallnlkvmtictyjsiacdudhknsecalgzntkaexyqqiujhgdpiguejbowvvaviivvdhxhgvaxgwinememsysdchzxebvmjsjmzgvgevfkunzyzfntffwjovjsweehwptvwrdcmioiynhxuwrbbplslukjyrtugorefxgshwcfljfffwhjlubyonkoedknrdrxaarkwtpkfupmsyvguygimsyqxifpmzoozhyxcqhjmzwhztnuuvegqdynneezduqebobgglrtmpanlphfmolcrjqenugspwwsmaatnsmzjooudwzpkksdwkjlerevncqceqxrkjsuzhqcfqtrcnuyxfmexsbmurobilsuwnoxufzbxvszkoaitooqgpfchtdrhylflcdhjekgbchmqjwjxkcdzhiicnzlllrypwhyareevlqlvmxuwzaxiigkvhwjupjjlthnknribjtwvssttstbaboyimjdbsoolwdyjvgmgbicudxpvqrwrhwatffwztklrnldkrptuavqdugylzwcgsebzlxzhjmyyxblqtektbbyvhyiivnoprrpfqomzckzzokrhobcrecnnaxvvklneccjssvtuhtvvfmewpinumcjnafkksxoxzzrtunnegkblwihmsxifzrsbycmtacqbcjvywkeevgqtozhgwplyjfzjinckqulugiuctzhqhzjvardzirxtwyfgmhqeeeakmuohvxzcyjzzcdamuzgsxlzjuppatpmimdrharmbcnaeqcsmbyfylekivzkrpxzdcsmcjydlmfrejktkkyjyevizusqlrrotkdyvbdjplawukrfxrqvvldwhxptpgjksmioqoxhzzftmwajnsgkdjhniwixetixisnygmhhnpbzndibwonbzuoisyhxsrwamlaszjamydomiypencioxyyrxiopzmrlxrgyaglblzntxeamgkdfidxhlnylcpyziealmnmzmbvlfxalhftriszwfzhhrhjzmjkywwykzvxeszszglspftlmtuukbflpqwoouoaujzadzucfhuwmojzjfvvjagyseoizeivnjxqksuszromhalqfgloirpcwdkxiuuxdrqojkglbwxiykagslucfjedpqaxmuurnkfzxnkymjjmvlsnzpreaxhpnjmzfhqstytqndrbocojngymvpqwmznbiacnjvicvbvihmmxoahbihqgzgenirpqttgcoohjpcuyqgweifhqmxwngabouqgnjmsaczjsddsvlobttkkiiivqlwgfoouabmadnlxsrqnegoyczkkjuhbfgjmwxhwyvyejmbdftrjchheitqtoxwvfficdradmbjxnlbugudibnomgefweesjjclotoshpbheoiyxedhhqwtzabxefpqqtdfmrzwicdmtnmqtqzaivwjwxhceyswswbgpccyfhmawzqpppqznmjllsnrqinigrrvtvvadiabhsrykjzzxikkczbthqdphundsifxsiybcrzocbnblxloxohgfmyojlrbvzyohypjqrausnwdlhrkyasdpqrydlzzkzptxgepxekrsixfrqrprlsgemghxfijgxedeivdnhxhpumgkfwdqqlucrebymkemokszhvmosvfojlzojcgaqdehhggvsmhjybifzwvrraiaohqxlwzbzelgmteeflpenxlmvhfmjnoxwexkdwlccwlorpvxcmdrmqfhxlwjamgzrdjuawyevjvmyslbeosshaaglypnkwobzzxbwyithdfixrldhfwofwvsukgeiwikwhnufvnpasxxlidtnzjtvglrttxbshnopbiwyvlzpkydudwichdxqqqjmomexauobelbbycqldtfgrpjbsirlzcxpodmoxbrdoteovwzyshflwompwfaxhzmwcbmbceesxkisgkrarwmnmaeiiggpfunjvamzestdccvlmoldmzsyaxjidrzllpwjplivdcyievtskgxygpzioisubrhmgdvxoguuiucoqxicdfalwztljlhkfozntwnkchemciasfkwipyuoapzjbgwiwvvptizwqdiunlosxkdnkzbkocwrkszgpvvifxgxcrrqefibrlqcktmzxkshcsptpneukmsmdlcjtllkydolxdkunceswtnlgtiqgcflnhvguittljeimqfcujwtejeldmdloiweqrmmbhhnrlxfdehandtupxfsmkdctbilvvzwchltslzypiyfotzdsgxeqcjldryamyxrfimzvnzlwzsmsrrlzorpsadxudyidvzkfmmbzloxlgjdzaqyqipiisgeyenbbmmgayjalzxwfjcelseccuknosrmweqztcitcrtnttkyeofpilrlfeawwrivwyupceieyesrzerdsysckmppqplohwlfwiuzmefkkxbjbvovtjydvpvnfidmeprdxnejtjwejzpmuekonktkzozexcsdrjioymasyywhxlvsrfigpbspsrvruowlzfcyteqrcoazbwttddbvhumzlhshvovxvzvpdlihbjichcxmhzhottpgbhoyyjxtisknfvuggcjnggbohjcceujhbzibkfxuyhhdcdmihvxodzclkgpoaywexgfjlcdjmmpqpbzxyyndzrqlxgcaeageglnrqtbcfsuxtvutujwvanhztjbzjhdpvtglmjptdjqyfkrbtqwvdfvyowxzedqzyzmmkdfomwmtggwcwvzbaubrtaszdjadimpspvidrvzzdvdbqdctzdksdcrjnrmvfeqwzyaqvbpyyvbpydixiusoyjzmdnhirzhqyhivkawivxzvbjuldmbcyaqbqzlhzsazbiunkqwhovcrfxekjgohiekyeyifqieusyhfrfmfbpygocsxihhrltrrrzwkhcecbnhuttzwkxeyzwykdfdryuisuhcdjihmxogqkhmwgqvgnvsscvjapsujbwpkgwxelduxljvrbqzqntjpossemmpnleardxvqcsnpdijebkvoegguayapkqevwepzdkpgeexhpiostlizrotrmugxmluipjktwbbagakxjapelxdcmytyucxgnureddzzeccmdojooasbyxdmxymsakaperwjwxlbappfdzvtzpuzmzllxyfakgroznzwlkznzosennxpctoqscdqcjsmpjcqzbokmnxncrsreriilzadbdvdmprdztuyukkiyjycaqvgjeoudqagtxlgncloxhorsycqhaujsppbtfwyvygovtwkviaspomjmqzvqveklvlgajfvhnmfhkreuszmglvvbxqtsxddsqmtatqjgeqppiujctvkgtfjqyckvhqnxcixvbjbfztwjnilifwoybxqbbbwkrmhtwhoxsrdrkbbzjohpkxnowepzfojwatrychwuablncommdwbpvqfsuxrblbznwslyzhsakdpnubzxzcovsuppriselurhmefmoxoyddlzgmyiqmfliuazcudoowxbbdppcfnhkbgqneaerfpzbrbgngucdtfgwwxbbminnpdbtghmlyonpptevpgainihjfimsqzfhfidlrpypflguglsxtugjdpukxjcpikzpuccmtbdzokvvxnuskabprwzofcgpofhijjwcmabbmxylmsdwvhlmdndkrlbfsipbsmfgojhaihlucqtzxglctqomdqrvyhysvezjpijxswtuomtovgsqzhdczwazfauagvjvdusxsmtjamzxvrqwavabrabxbvzhqldvhxtclmlmbklydbugwuhoxinadmhvzmrhmmjxywlbixznyvtxsblplaproymonfxzriwqeitmhvzfqveiyrzjrqcipryewvdodbghlzvswwanrexpwzbppfxgwvktsnhvrpecqbxnyzarfzjbymtqrqwoohhiwqenfksvmrkmqmfvmozmcscukziagrjejnsozklndwbtihowxnjahnwsmgsuxbziqtwqfjfifhbqqrmnhxpollizrbwqexyxqwpxgufnbiftbshirwzckjedmawrzwortdobzvqdfwwgqdrhcsmmbsrounhtkvkfaaizvaccwzkoebygvmuxemlabsvnpfsphcqedtgzuqqauohpcdmirzivtdikkbbrdsxgndijvyjposhqytypvikqntbxwcqbwnkjssspsefiwoyujymeiwxvryupckrzzbirvdgnhnvdbvanvmrfkoacoutrgkqfnayifgvmwsszpummgerqurulnoetyuyowwwcduqbokaeenqktidpbxapneioahczfydkuvljgmnmzfzuncjpbkepqmftvhsbhxncszlylynmfmgtopkrbqiuennawhdeeqgyprzcrmowywroobfptqjyfyacfdglvmklfjpwvguvcmjqpqijitjosjqzmwnhkxupimnfshawvlpnbxymshakqqmeoznyykniwcnqjpohafwdeupxdpjthvzvwmtuyicwkrowgddgibmnvbmytvtatuwpgpmhyodkisgoxlxfmjuhmghfecgxwcmftymrprezjkngwmokxkuktbpxgxuczihtjtmynqxwdmfbbvlyffiyjaxpxtrzczuluilhzkgeylfziqynwiohilwifnhjobwljhuktcotpvtxmtlkbzrkmpstebctcyglhcycfmwwipuwkhilibxuqrqussputxicagfhuddeuzcegkpqnnndedaxvptqpdtrjyygddkxfpbnzqepfrsmuslrkeibibstbszexmbcbescuwrcpmolgzcsdmtnpljqfiwdwzhxdzcyxvcmhqxopcguockeptwvdvyaufzyhbyezargsqwhufdjhnpwinrmirmdzldgpdkofbwxuteoyjzhzofheztmqhrptwvkziaelwbfphwprvysmgzqfvcxcwpxsmhsuhygeanvhjnafnsrtuhyplwrjlpczywuguamunjezwgekbvfveemrcddpsfotdizxknuawazvidhjamggsszuycjsjylmikktzgvwgutozaaboihhdibblntulfhvrtotzlphfkxemzfaiohlxlmkyhelmnggixzjiqqqaqwopcoxsyirlwbjeiwsxeygbzpmpieryafnewlxwvohssqyaoqxsrdwqviidobbojqtfdsbziktactcqjaicfetcodhcekrnbmueexfohapjtrzdtkfboaffdnhkxaxyoufurkjzlykermjnwrqmrvrxibhgdegsylmsyvtjvdpnrbcnssclntiwwpeawkswygowejhznymgzouriwfwrnuofyqdkizkqqiuzpuoqivpzrjhpodgdxwixmlkwnunfdjeudvkoscnllwabciezfpzrcjqpfduiobfuypisrxdetfhvnoxudwohhexojtxxucrutzmnyptsudnkrmewksdtycihwfhsjyneaztyrnsnqizjuehhykazpiglnpdyqsfquyqlnlaftaopgvwgixfxfokvdpvtialczeporpumribxgaeozlnezxbdozywzmaaqrpjjjvatwybyepxpeepdfuseuqggriyixhricuebsfzyxxndbehiazpvpvbidctdjmnogtpazqyynobquibvjqsbnvjsxcvjplxvgoyfewfumjkhrneddweqrnjpsgyhtxealchrbnlnwywplaurwyhoosfgzfpsctspyaibpwolwncjnxamxkclchrbogwqcqhaqrbhnrujgxtwwvitfmfivwljfhbhaijsbmapfuamtvkprulwgqebosktbncbvnuyzunhqovkjfzczmzfcfitxsozfrccgwagsqpjgktrpuuohttlabeyvtzcmbgsaiasapbgzwaqtueapznpcspzlqbzsjrssumulqblmbbfxihohvfmnxlanpbfyszsomnacqnqtqwsyfgianupkhrywsfxnfrbtgdxyqnrhljhtrptfllrtcprxvqkcinxqrupjjgqvsyolpjsbukwzznywekyoxveqrtabcsyusmhixalukhvjwticdiqcgxtxsvyupyqikyskydeimztksbnxmfzscupxpqrhoipbgjleoroaqhkklzhxhipjdgiuyabnisqqbbwpsmrktcwgdffknwwsdendeqqojigkjecdqtpmxlsbsangwjabqnfwexlpgtzrybxqvmcuaxvsxjatubxkqchwzulfnlxnoueliigifddcofyjgvvxxctywhgmudbizhwjqeuxjxaycajlxekwhdufkatgfxpeenqwtkuchwclylwvqirzolgyocftbwahlqeeaaijwlwwwvrodiprvwsjazdbxfiivooxowgmheigzbudjkwfioqintlmatuvkisahanqhxwgzrlkdirlyivmdgaebvblfwooybffabmbuzmxhfkinfwtnwxrraezwsnvvsawwozqonqxtafiibbxyaopbtqxleshyvfujelxsdvldavncxlazzrasiagounmpujzywyqztdcwzsaydcxetaxdaftuuikorlmnjfmfkqbliblldjqyjpelwxhqhteyeslqzxvmqwzyzlmeueefvisrizjptpoxjbpjbkxriwphhdndjskeccxkcg");
    S.append("cvvsufvkqwwnnflfoticwswjyylwlncpkahhfctxbpmowxvnqgrpanyynlwybbplbwuibnfeftsdqqqjwhaywawklvkmwaazyuhzkofrhyuxqlimuikriibwdlibliakfjlrxtigrcgjpoxkxhbhredxpoydwbpdkcuivcqohwxyluikdlrxpxuhlumjxbnifjsboirvfrtslecrrvojskqdgcpefeomnipbqhyxyuplyrernuahgqnnuctbgjotrdxjmvjmpyapsehhbhohybwudrwlamftrcfgxospmsegzlnatejtfgplxlwazdtrfyxforubmajnmxnhdwaiepvcdueexzxdawkoogrcgbpuaebdcdnhvllafpecochqarfqxwsgzhaznceqhsxcdkcibjkpnuinlneljyrjgtktvplfjkhmumwvvinkcvxqidbtcueknrflgrqrbzdylbtilgsodsgnxhjzdnjsgpdmivdlvijildtxlnnrftedcnehdqzjhdypadavmmgzpzycoltwgabknfrqvtacyytybuupgcrxdpbrdxnlxoykupdrkfuidnhgjdyrfrqiguzlrpxodvaxmfpxklhpaoykrwdynwgvqihhxgxadzarfrqpeyfemdqebwcqsllivurucowfnyxuppkvnrcauugxqknixassxjozzlhpjgjbvtwjkylgvrflduwbvovuaktzvaoggyhetgnggyobrunuojvlzrfzdbbydremroqludtadjcudrquamhbfpbiqeitvofncxawctzfcbbisseuglvnayxscfoatvnytnuvitdmwguqaakimivogsjlxxltsarnnnlxjqjhwgyzgkhusdlbxumxtzqlzjboymnjcwracrzlbjfumqvjkjgfiyrjjuzkgmtkimkndmyhvfbjyyukjdqrdjcndbzlnuvxhhmmvmvqsybqwwafbsyebctefspsnkswuekprgilejddhfkiylpznigtbvnqihmemytfebslnxowcafcluezekufhjxprlvfkpmqsebhdrefslkfhoelaathkqzdcuiolueeaildbrkvcubjkhvkiouugtxkphybdfhdpsiqmsoonpazwvkglecrzzcoicrlhyowtwpfrftgutfsqrzbvbvwdxnlzezjdnhwmydddyjxuoqkgrodujcxipljvksyzcwlazqeybdsqveymudjffinwtzsftsfvpsyqlcqmhkedrerliarpjgoefimbatwrnukyyalgnrlewergfixqrlwytizwbynpsxptycbnuorqsiyztvrkjoieykaciotcnivnlyahmrxfcxgxsyhdorimbiqlyjomsjzfkamwtldxikuckbiyvqmyadkzmyngjmntobsimvfwhabrdjrabezbhlcvjpeuerqstichfkgnmhgrsystetwnebipsojwibrdjtkpfqerzkjrkdkowjbckmrcacbieslpnyetqkkqvvhhyuwwdzyplptouuwymknckuvaehqbdajduhazgyqsqjgkeeksqzuavrbcztdetojpvjoekgyaqgmwkwodxjdtagvbaahvprlmabqkiomauywhwkkuvppwymzamhvbygqakgnsxzutyqawamswehuctvcgcxjcgzdqiqkfpeypubowpgutoqvjmzpozbrhawxkmjpdpqvlrjjgpecduzngzexyhqhkmguapmdgcytvhcdxgfcdfrlgyigfanpotmokyuqaujxclabugfxnzktxvzevmwkfiapguhipqftdfwzndwccjvbewgzkclpmmgsgpaxhhslookesjguseywaexlijcuvlodaqdkxvvnmhresyameyieeirorukohytjowtnuqbgysmssmfwnndtxwioqseztyjjwexqvaytuknpzsnoiqhfnrufmahfysqvwpnrpmmmwabxsolahtiwmedyekfdcwablnsdbfpafqxzgjcneklqoppsbwxgsxhenzjogyqnoonuzrkdbxvrmmjqjjwbgdhvesknqaaeqpbpgnujkqazempnvnrdsazytlljnmqebtvkiqtsiwtmikcfxtipvjoouzainqorljbtbunlqhyatcfrvqiqzwhuyhelzuekfffjcsuubuvvwcorswksvrotaugzoljvsoeuizpkvmhsovljrvdmorhonqnbdyyvodqxehjztsrjnfsgavvroehbuxexvdtkiljmsulnmfybkthmoedfjlifknffsyralcmfwzskeqlolvbczghktpsdexjyxcaifmndohoszzjlwknwsuimlapszaqwdrekftnvadoadzlzlyxduipfkfzkfsgijluqeimhlchqbssmsrrmzhtkmtutlccwpyowgcduekmhegvwynkzwdliyhblkfjzgyvocyijkxsodomddwfbcqjcymoiuzjazhocfzlsydgdtvjyrozvekyczomvnwxndlrkmcemhnqdizbbndxtnvwojkjyignnueyiievsrlvzmybbbaurvqhgdrglzuztazeldzdvmawyyxzcztvumccptlavfbqhausetxzfxvfyveauavroxvftghonjgvjbpnunocmmhkhomdqjwghslcyssmdyjnzycwnraadjmmzbrpkoxtgivgdnsgobvpcylprlrwvgkuhbmrrjnzuigzyahmedbgdpxwcrszxcwefkxztkypvgovjwhnbkvebuaelufiybofloehadwvlnqujzjmmgsnbutmozxoaltobyvcdtzogslrtudhbvvepqqfrptudnienfiqykgfctwxocfrxcvpsnwmdqggfokstvktfsyjmxgczlvypqpifmwiecqjsddlypedhfmvcuzdgudvmtfshbcldtuxwziwopzvwntahsfpninzbstkwwvbrrzpqjleamcwbvykndacmnephxafvvhmnidfrokscoqdjpoyuodgdhymgysevzqkhuqakcunqlsbcgbwwgfbndowdgaahslqodqzhnoxaiacpdankhpzmfvrlxsuqfkeicfppvoreqtvtwidfzdseajrybloixuqcchtxxlbngwhchmigrclslespqixpcqreznexyfkeetjlittzvgtillzdvzsypmeqzknxsadwtojbhcbelwpwkqzfptfgfmxewkdokhkgxvhfaklljcjttiyiberjqonmarbnantxlcenpsyyuhrnrryuvpdnftahmdumsplfussjksjebasstspphscmletxjqlefzjztekdwtjyvdspvhgecksnronceaglbsnuwxzutlsqolhmunqwajkrxztbbcofubbsbbowmhmcuuoojfljqxcnywqzyeguiioljchxlvbhrqwdxjmngrcgnshnyojgwzrhdaepwhwpxuswdhllynrnfzaenlmlkgsniwegfdwdxqbdxnovagiihkowvahhizvrxzxccvhcmjfjythybrikcwgzznhhmealfaoivxmhgrtgmcfotnyxmhzlugujmvwrvkbqinaqgvxtxejstjqfqrsnznyliucdfevptxhasgtpmckincnetitsvrfdlhisnfjhdtjvzcnogxwtzlzutsmolrtxabpqhbzmuihvmwjsthnutpomjcdvvwswyjohkntgvcchznbuvqroffcpkfkfcedcnrlaeegbikkjyletevjjcnamtmsxsvzipmitsqxabhkbvqwznhkpuncpbwmutncizmercbufiggdwcejjpagcevfaizuxbexniqsjkzsqgxokcxwtyexezcfjcbhffgvowpxjorzwrqhtnfonjlskpzurabzhhuvfjvozoqwfgfyvkojvzdfpebigchnldlrogfowvkcbpcxbriyqnfhrhsxjbmxfxotftjcrteskgrnxbnqotzharglttiasyvysfclagfloxaoaogrhfyjzhwuahwufrszwukxcqktpmhpangjuhuvorrqankffdhdzrcejoxybgqdtynpfjnvaelcswizbpcaihesvdsyjkcejrwqajmdpxfhtpiwenxpudhrhbqsmuxxltnnebqtbydwnltuyqluvsmevexjywglrwyvzroaptxefqafilcdfoibuhkudtfurdrquhrlboqggqnocprhfbkzixtnqajjeazaiqmjgcrmueayuugzvsbgpwthxdqbdqdnipwrtnfqsgoocwpxpqdgpzomcgphysvjktafhfvbnkrpigpxzugotucxcthblekxxmdgrajhkayvhnsiiaydwadztkagqkenwjupjdywgxrpklxumvaivdhqpjwfvcutlqjxwhtcgmrrxoljlnugqdyhnumizjcmznjvwexdqowoejzpxtxpzvxmslwruadupnpmtnrkdpaiyzzvvxluskikvedqfhkuwzipobbsqbcpbbmyyzstjafhgjxlshiqishzujtkkvbnyqlzpfsbhziokfbcojsycaxylqkxnmlmeyntehwlggbrndvdmqcqsbqdsbahpnvtmiosplfyrhrhmmkaemtwojzikcrzrnudxjxpufnnogwashaxtvvjfxtpzlrqalwxuvoqcbgqhesclvbhqysvvmnwzepzrlyoinglqobatueerfnhfgdbsqxtwmspzsfihmvyayyvuealjntqvwrnorimdabdvhncvkcxictdgvorkhigfzvvqehwglbduogryioinjltgclrltzrswolawhjjimfyuwyfkgswulcwwvhpuakrpxtnecyordolzoqlwihqwkosfykcauvrtoeztcnmhdxgrqspabgwnwptwbxxvsooivndzcphhnpafhpflxcyuttquqprkghtbwycaojhdbhikynvwrusmniigazqgiljgklgaxpdyylixaetualgnvptvqmdzxmfjymjxdovcbfzcjswjfczatuykgbjgoqpoeblipikrnjvzdrkkvjkrekbmomscckshlfpcnzvakqbpjmzfxlydwzqmlkqnuiwhnlkamfcywenhsooramvylnkwxamuiibxunzivbhrhwablptnbqlndvlcxxyhgaecpfmkgvdqsetftosuefjjavozumgcomgertqzouriylptzhpgmzrowfnqsvtihbmaufalzrvzelugsjohkfxglnzfiunsvcushudioyhujbcemqnxwkfjzgoiadcyhyqovtyltfffiilxaywykdhkkxumbykiwczrxxcrfkypujouhktroadyaxmbvxlsbmlzkqsraqiuzxoghjzvmdvmainyhasindjztbqvrypqvgyfpycbdynficdrdvbsblrwmjsghjwfpvmhvokyoadychphetbhesesbyylhxsxsegpuqnjqqhvzzegsgnyarscrrpzojyipexjwmspeckebqmvwzvxtnbkeslubjeqpbzmnubuynybdkwclrbgecrluchxomndporfkuzihxjnjhrwasdhwwsqyzponesdsjifehpngbcritnlnssjombpnqdbuvephounvladmfuxyjiocaxqozhbrlcmiibpwxctkfwxksuiceygewysxgvoryczgdrfcvypavmknivrmtxsrwmoehdvhfwqlpvpltelosweatvomvgeruuvezslhqbuiwjtqqggkemlxibmfksyemvimnwvfxppwaioqpubvjottphybzwglrqzgmunmldimlwecshabxtupasqbqwohpqpbmdnygvkttqymyynvtkkvebwvzkeoauspxfnfdzywpwknwalpkvkoyilytqovkgmjozorympqladhaqzkmjamsmmnebkojxxxylozzepmgsugjtsxeujygynmzacizzixmwwoclaigworriowdzwlxmyymajlcrkqphgsuhcaftrxbhvgjtsifsjrssewlppcwvuseztmsmjvwgchzwpfoyyisdhfswiqteiaddmxsxvqdzxcvmjlnithncnfyxxubhuhnigffbxicumxiykvggfvisajrzdsgzbthiaolxworknaiyxqeelmwnxpmaivbjoitpwfouvihiirihiomvdklaaxdksevjnralyoitwpdggoqqceohoziqylctpacerespsairrnxxbpqgtenkfvadifyafcgygynzaxsrkprohjuaphkufnznsypzcwxjwkkxjcqttjhasbbhlndqadfudvtftsopeaoncfjwosyhwzqhpuzpxekczfhsaykeyyudddbuxzdtbtcqnbfmgrqhzyczymfykbbqecmeepxfkcvadcolwftxfxyfjddvfhnllzppqpwdtrdwwtkuyfrfcpkluqibxwnkxeaqvwvoqanotxiuczzgqmtkebmbzblzhnrfmmtcmiqhfabjzdrrrkbvopypsjoazhfltanhjfpqfscgvtxogajuqhjktaksjrfdsumbzdrgwvotpnixnrrfbcquecoouttmnfpojiwhekavzksoclwfnobuqpimkbyzipntylaexbzwntkpegwtuxpnmxzyaaxjpmcpzftwiusvnfntnjpkhexvcyweytpqiuhhzbghvyelfretbtdkychcadibzytjsasrdyazzekewqzzmclmgsojywkzufimhaflzfkymwekoelaiwmpqcyqbkpvrzkyarjbzvblthvilgkuexlwzjncvcymekkgdwhcrknnrkxfshbygmviviksmiequqwymfagrqtlcqqvzttgfoocyyuljadfqcdfjgldygoaygvxzrdehtigsctdcyiucmzmumpssfssnqqojvuqrcyletoiswcukcieqzyletdtjvhutbmkdnvjgjrahwxsahwqhvbnohhzcovvshhxuehdnafvyepymlrtiugybpzegubyclxypedfhlcfprgbcxuntdlvulbaheajpykfepmnaotqfbrjaowttkopxrqewsqnscuzrgmsuhkzidlirjemtscsmuxionvhpqiiyuctemuoqfxjrbykvadhucrirprihzqfwtaahvozcnltqjgojlxfppngghongeseztgjqviukvobewrnjouyfkelrsyzyteaokjghtaupqvvmolxcfvipzvgtwisrmufyfniyncpgzlunysrdnbvpxbitjckxwrybegctitbhgwweonkdpfsmidhsfrrekphqvljfflmljkszyccnhxbpaijhbdnlsxdqimvnzkbiuvhuxmjgjkxlujuxmikgfnwpcufbggmcbvzajiguvffrocpckzjrnhipdnsjtgowenynxeismpkarbfdaowpxfebszmofcmlplznrjhdytgqulvvbskyzrbrsohzqulihkxytectykjsvhpwzcwyvyrnobmexzoejlcesgyssjmnqclrgkrbijowmccjcrnnerswasqsbdiuftlnfdonoelagqjkjyynpsmpkcinfqhmlvztypgmtuockzmtxmiogueszogggpttkumkkvydrmfbluwkggzndffcvnibfdwpalakiypznxcaralzgkojrwnmlypqdlgdyipgihhvejwxjjysvpbmlrawppnscvwbdnjnurlachzosygnnjbyabwrqlkugixlvhzsaksctssnsyafkdfkebewposvyqjvzknzwidraweswgzgjetphxnwnfulvfgupwvlebcgziavozcxfxomublhghuemcabawoezsmyamvonznswbhqeuzkbvopypqyngfoytoygjotjbohevaipzuqxjfvaxvpfcfdwtdecrznunvyixizwcrvnysnxoecsjfqckwhbwwvltvwqdvpgpktndjltpnnzvedfcmjajgmeeivjfkpuwlizwvulkimfoqnqcyrngdsknpuublpxyzzuwtigbumrmbirzbgfeystpmnqzeoyfpubicimzwdlzneyolfulznbldrljhlpwfshictjayuzmbzamhvxgkyupsfzzsqyxkffnyqpwnpkoolcvizeuaykufltxdtunyccwssvfjhvxsxvczbxbzjveuqbxkxmizrhujezulxtguqitrhuqqpwmixlsxaawrjscnswzrpflgpfxxbqptmcbmptjpvhgfaiuuetlwaweqddnfteyigzuwsdlpkuukcpejkphloldroqwkwusdvobprofdibqnswwdodqcqtvglsqhokmmynbrcgxmipzalxfbcvfsobbzvrjlevxejzynhvbrmbuzyzbbxgvnjwpeqyqwtnaeoarwxvpjwjjwlmzisxvcvkiliyjcokiemcakxsvvbermqtwlqsplcxznwirmuzqhbttylototnblixwyrllwlmjhvavrbladetdrizgdojqwipwacjwnwfuthrkidlnjetbosjrgskeczacbjypfflyodlxgymrcfutzdmfxwjavlcxvdmckxdzcoyimkcqswjrcyzzuibqwgnrjbtvayudounafptzoeqqcppxatpwzepkfkfhjckxwaadpvcdzvvcknbmgskfwzrdmykvaxuzxucophsrepvqcydiqnpmpgpbfbyhysgvplmgugxyvuoysrgihyuyelzlnxtnrbjgmvngbmgwhvwzodixdevhqwvftoslpdteagmdvtccoybedlcgszqkwlprzjeqnyupgjtbcvdsvhwzuafmcvyyinpjzwaouwdafwcjthbpqaolcvzetycbnulmqnamlgmteqcyagpqgfdicdnvbrzqnmdfzfrmlgfqyiqyohrwmueqxtmalnrazwgabzmpfshgczeqgxmusskrmyuzlnbmjhmmhzboriutjblpyzlstykhwggktkwcrjoaisrbcnwmohdpiphbntjlzrvubuwvdnxnfoohcmvsvzhdoredkpomhmgkgfflufybtokxagqvfzyvqeyduordcxpiextyctzxhkvhsnexmesdjsifizklyzvdwpdakdysptbhgggumazvshccgonfojkmannmslehgiqoowicyiyqrcvyrifhtrtveiixtmgbwzpkxynwinozrjzkuernxnjcvtbrhjseikqiifesuurmerwyxqnvfyzcravzlqduatekbicvethimgimywjfukhmkdlxtqtvlxfmhhkmcrurzddzzoetymhkruhmmbhyymmgvwsxqoqrttrujvqhjxrcsbogcxecltiircihtyzmushdpxlzwedqfuxjrdmfwwnobxxuwyiqqptioghllmbsgacaeohamuqnsqhqqqgjhbryiajvmmntppvtfcvsyohsgpiwhofqqyoayzvnoigdadyhftbdjjypagmjxtbpsqpqrkfhunmwqkevefbnqzfshrbekdvevkxmwcwrqingttezbhoritmpknwxsrbneaccdattijbbefcforzwkloxamwtfzijammdmhyvsbyzjbexufwgqkfrwbmddupdjdivkisfjtesuksqqowiyczwfnbikqnttxqghllrerlabdqtrocrsdpyrribhywbvmhdxuwbnhdqntkgimyappaiiljqjhtgucltyjxlrabgulmezmgrcjzkulcfsxlozzizifkbfohbbuhbioskxtvtezyzxlnykvytciwkyelaatdijxvgqszxrzwokjnvfnqhxmrwsfwsabdjyyroqslhrhydtnyyaqhxfbtxirsisvtqvkhleuuxeuaxhbvjrehwhcupwozqjsjeeqrimnhqiinrjkdapmzyqvomjnfsioymgrxseejooyixjnaazzypumedcjxmzxahoxzuekjsjtsugsuhrswnhcqjnpvmmvtavvubfftqalkgfenwzmlgobresmqejmgghdjoidhlqrryyhuxvgfjjwpckgivupcbslrcqynjshhsqdrllwwxsmbcsjxmabpupgzhhoszvbtlhkwsgvryhscbebpedgbumadvqwwmxpzgzyzhtqfaljdplsvqyymmypkpngdnyctxcshvloxyycnlukbujpjhetyszdfziyzktgigufeskrdebgbkvkucduueabeizduujcadkeyuxdabkbnhljjqefvegbogeiapldsxhsrhwriitvpgfbxlgurnvhvzpvocyizibspzmzhhwynbbkoighxbosagpqiqbkyeepdqqmxmmlqoxeygmvhcniieatubdufjliabwzojmgzmkxsyszdhjlnkeiljertsbvtyujjjpivcfnczmfiwrvgxftjkkeeobxmvjveafbekohjdmtbfojfucogpukdvjrsuozvtazdriouxjxcxgjtkzbbtneozxhtliqgislzcbvzksekandlphoiwtzvsirgqjmybaagbvfoqjyqzddokswhnxehfvnxhcwkeyqfvwtlfteexjsjdrssoxawuptbtrmxpcflyayguemtqhctyvoouokofesbxeynifajgypcfdvvkzqiwexqujhapzbndbcnhcwpcnrhrmebnvkkzjcuujwkizznabbjdzyhnpredvfrqrdscfoxpkjzlipljsxorvvelmkqisjajmumsiljuuifcxtnpqbjnwpalarvsavdpsrhacqxwacaaxnpnoabnqdyqutixqnmiftlhlignkgniufxifaxvevlaqvxhjjrqecxrkdfxksfgxeqdhoanjoncjnjuwakddkuppzmzsthryywhmxuxljzwwktvnfyspshfsugmpqfpocwcbsbocdkynfvaltucmcjgysunizdwdbagubuyehysmuuznpcbtljtddglfljknjrjnnhrufljziipzkqsyaldftrmhazwmsfzfmscteltotingahgcujkncwwfxstpxsvlmxfskagditlkatnufxkhvimtbfkazfmmeteqtnudghnjbcfgbrpxacsbjcblkpzqidhdsdgzkwknypvajlzjnrmyvpjffiwpodtgibhuxtiwjdyuoxddqpelgnrlypdmuwucxcwxxgvimtcasfgiwkvdzmqxdtlzrgbrkaohilquesvyadgynbdmqwmvrxvjfycpyszhdpdjjahldnmsqphiltedszoyxqnykohndkwpldcosohkeqtrjxwbwkbtcptmdazjlxayccourxfxnaoyxnctxdocukoakqjxnlvqpmmijtfxtwvqjxlidckxmzxyriwettwjflrrezoaxlsglgqoiblwcjutrsznoyyvjrmpqcrnokvbsmpapcytnsofnnubnswwjlocnfrdrnejbvlsyjllebsrqmuegqasbqurwlupazzdukvuwigalytcnngtfilhzhaxvumvvnbsaymwhsyutrwiecbjlpsmyujtpxrmlselsrhblhwgysoevspijkuhefdvcuinomygfhiroexoyostgkdpuxyycbwhxeunqtwycgptmohkecekojbijcildesdkpsbkobmuqfggprltxxhgbprdzfigznqogtyuunrmsztjsgcpfjygaplauwgwjykpsjmmwfzqeucxceohqoqqgdsczyyjxjarumdstckaymxatnkjeczkfsofsnoryqiudmoyqyyrnxrgldrpfsblmpqfegyewhybvmannagcyykldbcjtdugumnzulfqueurswhhwnjunthtwjcsoyyjknkgapyxvsqqozxnnhjccrvzazrpxrajgmpgbofziwpzxmmmvvpyomzrmqgljivrumzxcfwldlkaaynvvetksubgmjsvrpmndokgthtyufyudnqytlkmglsuumqnbeafslnnuglonmjsbbqvmbjuobpjqhyrnzyiknxgtjuixdaagtvvwgpkkgvaputdvsnhfknbvhlsnmtthqodxgphonjehcjcwwlcgyfcfqwuerolieqhmgqbtzhcdqgaorewpjawbxriohtynkfasxdlwmpcevcsmgodrrzuoscezydnkkpeiupgggikbmepuuulltibomgnklcwvtafnrotxyfallrhgltrhdtvpbkamnyjizpwqfrqdzqqkiyqttkcyfxngipuxodfnudfzvvfuoxewxxrsjuqotewbtqajtgwojepgbuapshnrgfkpbafuebwatyjmeicqmuynobfiisaqxmfymmvtnrvgreuyozoctmsjnxuxpwnpiupwgkpvdkdvrytfzjksxcdnbxmqvchdyzafjrcpcgmtghafskzrmeenctgzvpdtofaawdraldvrmwyeixvxdckfxshskknyroantrqgwlzkhxoqwmnzqzhnuoheauerkfgfywlxikhrjwjfkyhcaorucyaeugvhnbmmaajkmqbwoldalkelavfevhiqodxbhlpjlataruyhhdktooeqmmvhtfzjlwnskcdxdwxwsxfcbpvrgbhspebjkbuvzhpjkshnoglieqkfghwmjfgykhjuigpknetxwcmmlxwpqhwwqukbzscjzlfkruwhareyutkhsjyisugiqskrkmdmziytduadlgehzoouzmcrilaadmsqyvczfcsiwsojbsraihukwzbgkdhppbfpyjrdljoheetfwkhgteqpfuwpzfgpioggxxjjjxspdlwbxdirhaiowgfwlmntavbzvlkekdvoqrpqwzjgcruyyawlmlfohgnctyigddwvwnlsnuyrmtrmtoxhzevinkmgiuonzkjxyuqnpqedjxpelmlnrixgtkodedrgpadnuvclmioxgfctupilygttqhwwkncanipkduvpcmnirrelgmygstzibyhlllnbtfnzzkobydmswqffcglxznhalooyabbtgquhbjjzoxrlnxqrddoeqwfdktemwfidwckznvacssphrhzwmnafjhiwamkxuscsvorikrmeldmeklczafemmoipdsoxnqrkeksqdhvrkjvxuawuhcgvqqktlysaqjkxtcnxlkoytlvnntkuxsqnjahwbzwrlgqekedwotukfrzsmjplefyphhlkjjeupuabygzpzvbeoazcmonqvmfcvqngbrcqldtmltdkoerdeqfqgtgbhndedslejnwxsqldloqzsheyxkpdfxeoljsqcfxnvvmnnxtyljadqecpqtuxsaldjgwnntbiqnwelrcycecujgjbbfbtfozquveiqhqgwomhhrijvkdgsoivvlqazptumxeeewlanxkuosgmejsyacvvnlynbrjpqnuwlhyygkvxcbjimbimgdktpiisbrhrgbpsknsrynocoxndavpizbeqlmimgrfovugfkbwiqkmhjzkmvbuwdymonwbtnijifqoptmxjxlqilcugerchrqvgmybbjlmodcdwochnnawycsgglkpfanlolnswyekbgpzurgihighlaqtoajtdwerjtlgrpquxivsbecimkszmitaietnaahltsrnikjfsfztjdexnyxagqcquwewwlhpdbcnnovftuaeagdneapkssdoqhmmsudxxilkwirrerveayuebjrtduktdwhxslupvhbrpsiukfymdsqeejajdkdigkfbxaqsjslbmtvyyvkeqdiwmkfsukadxggugyprsasfwpduloydrjbdvgvsrwxqedkmbbducwzyleuysvdlgerbnotgmqwqtgcxkgroesmpwiqrfvclavnnkitwbpnwxptjxmxbeltdujuzgynguavgkqclhrihjdqgqoiuzbzvmvvsuibqkywwpaiyssfdvyjlmtilwhhkdedzograepwvpgwjybmuzyhnrrbobkygodfelyesnanchjohncdoalcsefifpilfosjzqnrfphiyqygfajubwyvddfispwxdoidhxwmmdwnwuvdxexadxxiccqutmtxddcmaaapezcctxfsdfsiepqhthdpvxmaaqodqpsuouyjtgewlwfpovyjecycbmedctgtkypauqlnwpkhoryeozpvbqzccdfhymckmtkhjywfpiodzpjoozlprktvnycomgybalijlgpapfgoupeiqpzhbsfpthffodgoqqzbxqcmwkcszhdsycbaxgrqqwxebeuuwvvvmzhijmclvfdtigvfnmxdkujoafizadumfewoqzzleegolcvyqsdbpopptxqvclkhpgwhhxsocyepcaptuuujilbbirymciutrsaypsotttivtzoptkzbvceftduylorcgudvorrhmbfhbvxuiyyfacnvhhtvvnxdjcyxurhitoqwjpsrnqznvmkctgxotxrczrbvlieewwcilubylanimxmfdmvyjockieitomzlkdvvxwvkxksljftnroncfsmpwukmilzuouplrmwqwobgvuvmlkqscdhazrnkcmeshwdaibqhstpnqmcdwsvbxcbzvolzdprmlwmwyefszufpgjseepfdrjeoxpncfgosfcqfgvxirezzqseuhlzbuerutfwynntspjzyhwgydfytzepjpokmudgoieoythsjtsjaqpyvwyqycfhuwtgqwoghuvhoezzztizmzahvyavdfcxdxfwutcdpzkfgleffmftnhclfcgiklhnimlehqhxrrcxfjfkxmflthwymejwpqlydxcmnmjgfoekzjwwxbhqlfqxkztbsgzxdejssvzxlfikqegniqdtxvsjvnlptmmzqxtkssidzqnrwweeojzlfrvcegrasfzlcsvvnfamvqeuokacwcnbtidaqwalbbyfttcgbdvwvbsjntljxakqlpsbnumqvmwrgyzhdwfyxajnvuwsxjkiadjozygxkeosfnbimjjjlsygpvymgkugpfwpfdmshcmmroxdzipkcnsrksfqcbxsjvhoyvgizvwslvwkdnwgaozngfdiiccryjgygoihjhaucwgijeolspaauelfinwgzehhvrpeypbbljjwozjiokjlquqyjohitqqohfugwxjjhsfvtqdjcouskmtubaewqwkxdgfzeoilipidinnkchdmwwizypvqlhnrnvstjbgusoxacklguquqodrmumchvrykmreyukumbrnfcalyqfqekhqevumsnkccdiaxvwkwnzbrzmpptfveonijhubwhgykoaklwwjkvkbwytnuaiypvgyedcakizwybgmyevkzinxggkchsawpjwtmawyivujydvwhqijywihctllnmjdviyjiuhtrdcqgzoqrxbstjbxemlsyuxvcvenqlghpkzxjopclsugexfikqcknbgrxmepakwpuyofmgizaehecmpoftyuvglstshtcfkzdaeriosjtznxoelvylptsclnuuizzuafweyfvroqqdngiybgjqzdtmspiaohuoorvnysemiwpvdzvefgxgksschafykboaujnsbtglswgvxbbdkyjuqeyagdyqfmtzyiubcanuytrwxszjtkdlodlvhlnoyipyrlztmmpkiiihcjqvuvjkyuhhixxgfnewyfrmuizcshlvtbspybzwyjggidonqumrzfdtoexgmidflwujzvixgoforteknqdnviilngubozicvelxzvuijawdvapjsuyqcgdroxsfzahueurgqoobqmglpxwjruzwddnrcgwbidyparvqhltxxaloxhypexnhcaahixkrckicedulqolpoyenfeeldrsisvpcsbvwodbsivfajqqzowpwatjdnelhzcjrghpukqmvmrvxklzajoyjjipymcflhrenythkkvfymbvyowzymxjyfawrfcemviztcidodjmrmjqcircuomsworntvufqcgjgqowvzknnwfqewsymgtivkyofswxwegfvwbqwmflimbokuoxchhxtfoazkxyofdlcgcauhleupselhdnqllksbkunksdpvptdrvyajcwyvdjegwurjemkrgzdyduvsoallylubiuyldctcynndzywhxzfcvcujcfxdttgboeotihvgujlvjjcikjlgnhrsnoviidlyjzrrinzfyncjwftjqepeogydxtlhywfhxwaflbmlopunwowmulsktqnslyzjueqrdszqulcicyxyotllgdvcwjuhhwrecjxfcwwykagmyglpzyaidvkzegjocasflpkpoinykumlczabzaldvveccnqepkjbbwqpgepracccdcfderibomdqcvxtfiujgnyntqgvukzxogcsxktmzzedudwaotluijjdbryvbzvpfxjfkftlezoydmarhlnpxxsyoswsttjzstecyzpcyfadsduzokrnjlmxekrxdbyvueasdpwcvyfnbcosoffxrtwxdowgpvelzedftbjozoddsuvibcfuwcrbqqkhlosmsvrnyaexrjcldzuhczbabvbtllawbsktpchncnnvwphcrbjtckvghwzskaqlubhkdqzhdmdzemdvpmqrzlxgjxztzrhtsodxgdfjrkkxyosnckebxcihbjcaajvvzbsfezqxlbvjrpwtadxxhhgiqjksufnuggbmfsmtvhcowbjyegylnevfphykolgxthlbbxuyewmdxwddebqsinxxuxjjlpieztfcrzdxypcxngbsjtmthlrdlcowuwavqgxhhcefshdhpgbybpsiblomnckjzkepsewcgcjalokaulzwbtgegusvzcfyolnjpllypuabjkbfwcdzxkfdgxzfbefppsyhflhvghyrszwqeardmwhuvgawmfndaomhztchdcyxevpixgljeufbcfxonmupcgwecblffmcjjttgzfrfbwgeefutrokwviazlnerziaibuqcbjhcgpgmubfn");
    S.append("rpjozlnlztcybcjewbizlpctgkfanmqfdbtcnjnmcpmguvmszvskmudteivuulzznubcfrqigcqcacvzxnjkjodpyyjsmktbfudkltuuxkwzntykgjupzzfxaormzknghsssnpomznmogmznyvexxjvxamjbxyhesbyfqprscfgisfxqdcnaniznxkowixaabahtpaltgseyzbocfdetmdilpzhxwjzzjitbjewryapyefcjijewonhemqbkzdklnfowlknkneirjmauvhqkuaoiqrsswedklrixdwhqcrhmdkizrajnazvtsplpuozfhjekfldickwgwehpeschtzclpnclddqknvwsizcnmavigcynucwncyarldcoguatrwoaysnktisbmsoivkxxixqvcsljolegksiilxsghmhvfydckabvzrqclclzpphzkrjvqbdxqksvkxzvxaqxrgzckmdcdndnenkvqnugapdkmcfkcxjvgkxqydubqyrwndrdromtdpfpulbwkmsqwrjuudymbrjoucunqggtxmlrzmqjzkaxdjuamumqlvgfactqibmgnxzmlrymbakfjgfxarplpysralhjnvrcusrgkpulpgsamxzorpzinuznfiyugpxakqiuhdhwlayyzxmqooadiztpkrmqjjstbbpigwgrwerwdgouupxeqqtckfstqnorqdbwzhpynhutpinjzmpqfkspqnkiaycqbdocndaamwvbhimgxdwcorrggdogbsyewoiltjnihjjotyvqdtsrmlxsqoargaxcogpirdqymbpfbkyffswsewkuknoqnnvctkkmpcnbfcjhohovzmnfiacunslafgcbsopisclyguhudvqfhfmsfomuixvqfzraqgkklvkrsroxogcylpqtfepqemgjozlefvqstbknsakjgnhtexolbebfqhregekocbivcsiwuufahmwnlkznoaufknlqswgwxptbjdxexdgfrcudntsduokecwwwsfspulsfopjlussmwnrlijpcxzibvhxpmrggcylsoxgkryynnpzwaqaarldnsdceujmzjzrzyxkpyifnagjcwmcnxgcrjhxfsjebkybjxnavxfjxluajgezfkbwvitbcehepfhaducarkjrxgrhmvgsijqfukgwooafvjhydkzxukotqhafeizmspsobaksqnxmwfmcrlzlyngowhtaruplkjzljkdcxdovkcxbowooluchdyivszgeiisrsxtrajmmmkncibquyudbsnwnmftunlohljanvmajynljrdfpxiqsyphmwahloxuzgumbiokjexsvaxmaheufvbsrdbcrawsgshkbvwkzwcwybiyinuqkxkcbngunfntxufapekpaonypwzaltsvrczspgmcnzxmvfnljxcjophfnghyazwrbvlqdzusbufwzsjirlrhdanshurjupcxmnoqmkkpepmdhvlbjeiofvgoszxoozlboztcmdsqvewezjfkqoyvlfjfeitvhfyvgrxswpltvjyxsfhivifhnqotyiofpmcvfcqetbxpkvoebvpwdufsnwmleattnvquxgytjwivtwawtrtznarawdmcghxvcopcuovemfbfdbcwuxvizpiyuwacdaxiopmkfjqrosyrvcpqznybcgzyfbbkbasrpecupsndmmwiomyqzwrdesdjtewxfoowvqrklgqxsmfjyfbzbseikitfmpctgtqbjjnwpfwksdvsjwumoyuagqhvfmqlfioqrenjjlvdoqwxmqmcogexwluexkretgvmmkvvvszjixjwnvydbeaaswgrhxwcfrorbmpweohnnqjalkfpoiriexaluwnapctqqiouyxhlotnzftqvbhefgetbcgdrkwjafzxdwgrkzemgxeeymiguozvsfajszuugtrjpycczhogeumfiuldkkacrbnjahgiaxrrvwtxdjfjeromnzpxlsbdtoovxudxswqpskoxvthzkvoxxfukxxnslenkyshsraklkyjfsqhcnxcmcyxcwmujskurcdltdxfpwqxqieuvdwkhvjjtxecqowjscuofuvvqlsitycqbbciwlupjytzoskguewzarwqeoazodqresccgwrpumqkeaqygpahgwznnambfevjzxlftewnsozxqrvygvfpuhchhqkmxfxwckdxqbmirlfhddfjanyuucddvorsvoqaavxqwkanvgwajbklqkgkpjnkzeevuuxupumngnaevyegbqrsdxeshseiyidvecrtxegfrijoiqujglbjjidschaargxvipohtwnmgyaoowavhdgslkknrnolvngfcxnxvclcalsphsmrwsjhewvyjljgypldmaakcmmfzgwjzkavclejhbhsoqyphmswmugriuwiastzcvwggdpqeyitqjiyvcujwaafuwpuqiwufublxsbctmxjvfhsjdbfdcmzabwrlulqooemjfoctnnquvkdymkeqxfsydjmxxcmngyyoochbqavnkynyirohzhicsuarwmhfmftaicdycalpaynksestlavfuxzslgxxlqztvetzegoehcpbgjutjrafpzxafxarpbmkggkmoarvciwkrsfckghzhgbnddethsfnarbpsumwejjvboznwioepqruvxpcrvrsmtjgpvduemricrvzzkfqcynxxccoztgooruenxyrlpboizpkjwcnupmruohtkqhyhceubeopwpfeouozsrmmizfcnmyxcfykjeenjscfkhlydcyxvmjdlqizcgyhlyrxfxkosovcxicckczfpkjaatbczcjkacjhcvvujsyibakldltsveuvxwawyliaosiqnfmzakjbmuophhjdleaicagampcchmswsgvsqhvcblukvdnijotlozlcoqexcyuwerquylzbztmuybeipxtgxhknzplbnfysyooxcpadimzjqdthbonavynwpaychywgmnfqcftnuswkwprzdhbbhoxwrxayvzekmjcqrwdlqbxosqpwxqzgfyeyyhwtgbfumikfgqsyrpnkiciftjoahsyfzubhxibmqoryekxvbmhjorpjaflkxjrzgsgfdkrtyjucttzufjgvaxfbcqnbceaizjncthxtqrvlmwhrfnnikulokyqocqwdbmfwtdplwmwjjfcqvpyfljuyjkmocjrgxxcscsdyebjpgmgbdejrqfpzrbnvmfbjeedgyggchsuqgatjgcayjryatnwfuxrydugbdpgmhaenayyicbhmsfoluktpkmpniwpnlvauxehxefhjgoxbvloqwjhgvwtguktfhvzecidgolrwzozvaonyxuevszbnkwurswywebeumwtmksjmiykguobmqebaawtuurqavvvhrqtdkeqtcabwrejhimjkieheyxftucanuwrecjzfjabcemlobuzlbgqbfvuazwptugxxpxcbzffeursvntgobwpjtljsianchnzykdbljhpfykalfiahhrqcdbxnbkrkxrjjwkvhrmmryodexflgydibefqhsispbbbxnyfhktyjibphianhdvkjdbqxlimmlplznsgamgluiabqaqzfnkqsngpypcmctxbhsjjequaaudswtsdgctzrzopqsbtoawvsigwaayawbsuumxdqschrswknzfbadyulerlepnctiwnutqnluueptqcsxdubdpohicnzppsxniugyhtvdzkysotptnswehdojsoahiauqucrdxmiyyhqmagfpyduhgiqaythhtdxphqugececlvqldxbdaxlifdigyrnrwmifdcljhqmsnegsejvegipnoxtmrignvotatgjayxuleafilbmrlbfzbaosjuhgkcgfjrfhpcvteabqdpjptqqurgoccxinaqulhcuyertkswaxmmohchbtnrxsoffqvbdfdydhrxsltujhixolergukemzltrhuxmyxphoobaiwjrgigrpzajkevlrqcykbmiyeqnsrpcpyuyicpxbrglxdwptbneccobggqyafdrfzpnbhleytqwxonqpawuclvktljtsonxnckolxukrkiobvgqxdkcqhmjuxfmlsjywwoitjukvcawzhpwmzacjnndkuqymbylzpttapfxriusplodmpfveszqnrobupouejpgywdrexjvkyyxpfstatjpmashphdvqsbrqeivessostnjyieabppqpixgpcdhugakljhrdchxxxgvfkyiznyrvxzmzfwvlwiyhprktbhqypodbmldzmuxlvllcjiogqopigabhjufbikcwxfvjbuvzgibpspxjentmpwtaqybaigwflurpooowyroqhnbncwzrtuuluxmcdkjrecivswrqphjyvmlxjmreglpaideoqqfuuprnllfcsqbndmnobesjrjmbpspfkwbfltuyezbwqemvqbjthddyorzdjriupkbdfvdjuehmkvjbwswdxbdmsfpcwaynmzqruucqalflhoofjklimougtmkrokfajvtfrxysiffpttxefintyjauxmllxsnioiohfobotgfobcthmialrcfrrscolswbamigakutsbiuebdslfdeikvzdapqslruybcynyugacceffyskewuosvtukjmyciwelwrjszjwbpdgovrsslimhegtpsrscpwnopzgpkdqagxqcqkwdsimyogoizgkpeadtbjklobnvadzsnvvidigrqubpclggnjcovoajrngrthlichshrzajvbyapfgcqktlzvxqkfkdnvmreezxfzgddhduolyeyrsxmrpxwbclxoddvcbcdhfiokccwbjuubtjukgqmkcjbhmwlpaqxizlsdqqgzmbcfkwqsoccsndzbbkrcdqmcdehtxoujrwrbuhvjlzbaddbgpukskwwobejyuejlcaoxjjgwijwnmaucweuhbrxevgerkncnqqkzzsyjbuxowrqtxkcydzeafirsytfojrpkjnukirygkqzhdbigcmjixaavwhjhyyshootyhuerkganhiwmxhuujwpfdapbajkoxdmeztxkzxziixufeqbqmsvpsksuvclsizyhsvtrttcdswiznjpzbkrvxjrsocnyclhuhbocwqfqoppbyedbrwyqhpkxsxvwlfsuigweydupsmssqxvhttzdikacjyyudrolzmrapnphzjbqhwzapbdalgewedtjwjuxwnznaqxaayshvtjmunohttgzwziuietgrtutlrvlrpsmjebfrppmivlguvcezwnsrhncajoraigjmhrfwgthdgnzbqdcoqwfzkhbvxtvizhwiaeggxnjedihtnmhgiohuvjpvendfwbnwfvedjpjmzshoequneejbpbwtobamdniikontdyherosxgpmkvwmkxkdnmdzpsjzczpyenhigyceerqpomdylndyzizqckshjhfnwovfmajrtbiiqngbckccuduxooezxlnuzkbokkhenrmdeuhgglkbqhaeveetrtpqdqgqcnwpekvkeernpdqfmqmiusdnlsfqkmimzedeoyovehivnydzophkjmxweeooqxcqbgwqgmllwvgjtjijasvleuhlywbptehwmdmurzfnwowpzhaczvndpvootqfmwmkirxciykyvaumbdndckriaoyqhqvvmhfwaebifngcpdoqidulonvqdtgmmdjakqzkxaucpdeeiuuqaqbodygiumugpdgbufnrhjvwmbcuvxlvpolvprdjfarwdxzzfosoypnkqphooujekxbzqeiyezukzwlgkewitjvmbeapylvkiwkbduzjwvtxrffclbmgljsmnsdnebaozmpcgqmemhddmihzkugvfyjvtokxpknrwfvfvhlhrplfxsfholewlpreeddvotmmykttrfbkuoqrivbzifvvvocbrfyhaluyxnuqsvbolrgvohuyorvlhxmzflgfycevsylkvmbifhdvqatetrqboeatpibnnfkffwrcksmszpnrrjylaqmouokmylblsmqzkibucpmeecvylvlnlzdipnunaxbulttvbwsglgksifqvbdiocmyrbsecsfwdbszcotkczuukcmhfpqtteskfmqjgyikzcldqeoufiorkczptrlmvdwcrnoylyimavpszdpgmdyyizqbqbqngmrxbhxofhahcqcdvrkjwiiehyizbymhmdeolceslfcoxeruvberloyuogtpghwsnciaenusuwgrazlvbrpofnltvgibogvdjedprxhluneunrsqjjgqyrkvyuyobtrmxubqtldpyjmnbefmpmcnfmifjgtmbmybibtcmxrnidajjivywynbmxhvfcjjpjkxldbxctmlypwixsitsywgxfefinozxywkdyjgtwtrlpbkjmfbmbijjmorbcqycokgouojqhbdwengssjevrqefexhjphnestumheeqhhnnwvgoemlycqfjpoufwhlqbfeklidsrqgnnodtaordgxmwngbjpmbqtfdfngzeyhtnvwgynydtxppkxswmgxfadkhssizsssfprlnelqxkhpvghtvqscsxmzqgjrybldrcqegcxgmevjleciuxkmuynmezdeaphttxyabxgobdxojcwezgstgiikhlojcxdwxdyyaeodjossqqyrxtmdbuqkigvkeyibdgbdmqhvebqwronqhbukwmwgsuqwszzyfdbsvkpblfxdumtdppqlborwikudlwbszemupcvajufdizjxqbjupcqaokdzieuhkgdzkycuuhzciiazllvdpldexgfgmzpwitystcmojmquwqvidqltdqzcyekmgakqviiokxcwlvqhnnrrdekiulkspxgxxrnszyimvliggaptnhksnwakhmezjlyicocbolpjwzrezizormgfiipsvtteetpihrmvcbgabzecierzqxgagxudtpjzjyisquouvwvyybbghshzrqcznwcgmogxmopekfzfcwgazhihbrguhkuvhydvmjubzjhjpirtgxtggdnhuvdcixzpyemmogpprxzgfqbmpwuvzjewghaohzfhdtvtzljtikbpaddrzzozisxpbxdkwltbepxgaizksabudhlfgzgptdgwaljywcbbtyhtgifaihuoqftwgbnbetyefjeblfefelamtgnepjufrpdxqrbukzarievvjzcpnfeintoccijvxmvndwjqvjhfpecbehstmydzrsdwamtaaopekzwhkdockrdqaxjmwmnpgqljefjevakgzgnmmcowvtmvqvjmjwcsopbswmysmjyopnabpqzmjlhziqouoqmchpwebvwpgwdxjfbbvanemxgwnuqemcittfxucvkitphfvigqjvxxsftgbmmnquikziedfqnzgvebdmtohslaxhiytunjowtqeatjqjkyeozjcvyknlklsulnozzevswbuqmlbxlhnlqqiqavemiygumwtlaqfxrapdnqayxqlpgtiipmuzkctyymjycpxvrmzeebcdtqvzjdjapdabftadijemabitqjxwmcaiuxhdargrwgsebshlenagrkahvzfjdhrxebdgujxqrquukeiqtixksztnyzwcgobnretlnrbgjybtejkxtjilibagcyklooyfsrqikuqtsrpwcbtvkdishiajhnwxbudvotfynwshpjdskzpucaolkqxtdtrgoucenhcddwkvfqprpentgdfruckxvbglviplruqxscsashrhnhdukinemxehzkvwumjlizibccbujuezbhvmrisdfymgijdcwmqgoyhqbimmfslgzgusonxwnkcwtfaruqyirhausmgxfudgcaztpypciryezbxttxbmfbgxppuwlzdnzegocxrnprrouqjoomctpjseaoblngapkhdoewbvqulmluczgfuxomcqafbplbexkklripmajanwupxdurakfechjdzwhdvtpjkayzajzpgvhdydxbqefsorwnpfslykulkummdnclijrjoqkeurxikvxaaqlachnmbbaeocsznjkczuivnfgyadycvlfxsultwmzkqrlgcvkdrszfyhfakdfiqkhietbchanxuadwwncngbugkziueneuzmegffimixlcypaueejkhesrfbmkoqgqclwjsexacjeaahueldrtjjkpmybdrhtvupfntepxnwxndtsqzjjiuwzjuamnkfjgwqvrulpihoxmkpcjamtxktggvsufzfoxvjopufzduxpgosprgkoqduwbosinxwmsgakaijgeivkxpzovjclpyksocadaxpudpefxonbwjlvymjwynjpnthbyheyetsjlxrkneolpbhqosmoivdxrkzprfpyzjjmicwvhkjwmsocmaquwvupfteyatybcknlmmityrskjgoyjguhbowijajkvhlxrzkgnvblmtyycmwnvquwhqhqsqanzmmtnzzaualiwlgayepvxjtebawvsxkulwnaxocrqpaobiqbpfwoopurqqmknweavrashemyuoovyfypkudbjxubohmdfsecmgxtllthziczpapzwuchsxjigxlnprqdypxlasszawaryptukvbeummcjxqbpiepxdaxwuyyqyqoqjkcglgbauflrvmiftzcyzbutzvlanzqwlsexahzunknkzyqsxsszwnsvkgvxfnvthzqhjpdwbtgarwuornnncikmoilvvgofhgoqxsozlvbetwsmjibnsozgwmdtuuybcpuchkduheybasplatyshzafngyhczjeofixpltzqrcslrgmkggrijwvpxdtyozmtilktejlxlgifmwrudlcojyfgbygpxskhfudjgqitwrakpsfsdhimfnvilbxlwhudexnmoxfmogxeaeyflhrtwobksbxoirrorgaheituqsaqybvkkofgdgsvhplsheganjhrrcnhxqzvruhdkmjnvcketxwccfcrojifoxolnnighqjtcvhzztestcxxhzoxlyezxsmnfutokfipcrodmuxhxueguuehskiqdudkqybbflsheiksdkaceekazohtbpjgjoqaptduexekfqdkadofjzfkppeqepwqgpsvivrpuvzbmcmmfatrzbbpqfhwxemdteadbrsvnszbwlpenjvpmwgcenabtngorgsvxksmernbcdfsoajaypeeeefarnolvigvtahzsvcwahoojxqkowovawvujlhfupappjohgregauwkumwkkkflgchsoqzifbxwklerekzfewraknlvxqigtryjhxrmyoikbobiybnfzghjtsgslqyghwpovgcvwngzdhhekwjlcsvvldqyuoebnvskdwgzjmanvkrigctjuqghuwfonlrcglmjiskrglgcvzmbbfmywvhqtngiazkhlxqvrboyovcjuomyvbyryhjrurcxrmeelwzfznfnkhyhxnhsqhmoovhlnjigwmmjknokctxncywzldubkipydylsitaghopsbgcezbtezpfkuznghegndshllkttykncqpisqbwqsbcodqumuabzujmgvfbtqoxiffavgynaaqodiireltefcqnzwquoeswjxzhgxugzmxewhwbupfctvpstimjmgiyejfmgbugqrkmzintxsxqqnklvxxjdfjjoqvwsqekjrwkzkgxednwsykpxhzcmkmcbjyvbdflubxtndquhgszgcpflqhzavbbhayiddvzhtswwlsftnfzpwedmncolessgqcluakdtezrpuzihejycpbodecqkwiqnkbhklmfpnzhhyroebpgsqwxwangxvykiztumfcesbnazeqfhflayhxuhldggdqqfjlronxjqklrrdwyljyjswfzjkiroyitivmykmjayutpyrqlaousmbtvobncaxxkslkcxuewqznqqdbquxiibdgpfvthsqtmbdqbzwxsvmklsmbmllwmqcbvzwermxfqdvrerqwochzuvciiphvglydlviejfkzkhspzinplwphrrscjzzpkzsxuacgimhsvajwixmvtbfnuxpdyosinngknkqxyzqtzzqkwcfsfmfzjufhkxsxmajakbxkbpdcrvmpxuhegoesklzuktuxtdxffwpiovkfqzwqtgubuatflvbwovtkbspiohqecclsbiiwodyifrprorndognpyexolnvnsgitoqsevqpbjeuyxhdmpqbpuzfioavghdcxwuetmtwxzqvxxajoqmdnljvcjsksyoqukwpwpmuocrncswqkjuzwduyoplhnzzjkvyimmccpwqtqojcxdcwiywznhgahhltembqygunckguzswyxyugzrhmanpjwaccmeunkocozjkadgyahvigmpuoejyamaeagynsnqiobsqeboeijpqxekmjwmlimqkvoyndqwtrqjxrydbjsrjxexiajtkxjusauidkgxdwgcqgirsajbzpyhpjdzgovboqlucrtgpbkfisxaviduxbemmjnuxixoaiidzblpcvwmqtmlbrhaulogwbbumaiojsrktebxcyksrydvskvmymtupwqdwyshqlslqtlsrxklxpipzfczrxwhtmkrnczfortgmeygbkryzzqagsaypyfkkrdzwcfkvysieqqfahfzixbqrdoibvsonwnjauvwsngyfwdfflgpihfalwiordcebqpbmkzhzffedlliwrzgbhiodrcuwfbxxqajdascfyjjjutfovnpcwffphzloaknrkzwkraspjdygwfurvltrqyizatoafbtpapcuqujbzkhvrdpmqkfglngliwnpzhfcyjwqffserbbjfqxxdfeoumbdkosjxzlwkhoppgtgkxrrnroswuiqijvsaqexrpvczktqspycmqvbjkuhecznoxhonllgdjicvesntqsfinitzcaehctvzaloowurhdtchnscfmjozrjbwkhdoaanclbqjvjracvjrpnxbwndowekilpwlplpcoinmrgmwcynrlmtibwyaaiinzhaqupsjxfvpmjnqjajxpefqafqcnbrokreehwngdkxrwkjfntcczjuaoledoyrypxeyvufzecsapeiczkgywaaknfqbfjzuhgtcaydecyhtcpxbbdzxgakthavmlwgscbxczajeasxchxfkafighsaouxcfviaykzclbnrcfreookzqrjxybzmxyqetygtkfyyhlpjtiphwzipsroqupernuyivechkafrnxcghrqgryvlsjcmyiendevaizhsbplpmnxtfpbyqwjihnufwaubeqbnpihtxtilghhnxilnhxvokrwgsqhwiklyvtrsmbgukpihzvwgstvdlrjfjtbsfpsailvwmbqysairweyoavrnodsxyvrdkjeuubqiehhwhrndxnsxklkyoeymcpsmbyhxzxgdgpzqgovdepdobppruneweykfwtdvtlyzczripnntcsidixxemdasbmaxgfubquastuckhrelkorsnoybratsmtruylvqfykasokbiqmsgdmtrkwikqcylttmeqelqpnkfalnsyhzdvuvfrfrjiqwbliidnvuejejlqzdqgctcxqjqwdbgvxjggtlvpfbammlelquutpysijkgmlmhixpqrbniiokudvyqlukgmkauukcgvqqgnfwgimoraylfvmwjigputqqncuvaklgyteebqvsttgyrsuacdrxwhpjmkgmwsswlwbopyhitpgkjdklgckclanxmvlknfhaeirtiybbpjmymyjuauyownnkjzuyebzilsdtqexhqjfejozmkxainnuwopuvsvdgnhzfnymfjrktijfuiffxuztjoqejljpbsqgipvnyoxrwiyttdyqaxklocpznaoovexdvusuiqnfamatrymrxycnatmpwagrhdwcxowfemmhyocpoojmolzohmedmkuvpqyoeuvliiugywynnjzbwaqdmwgbgcwxuulbrgmzqiipeicrrdvndltsjztreroyozzhhngyvqpaatttwojbbctkbobhipgwqfdatlyqepqeimygahhejdomtqubhcxahcacoatsplyzzwfcfogqtufnmbowdowmqypeykynxxoxttzbjfrdbyqozemcuiplggrgsqvpnjoicpjludoybgobnafhkussamysctkpcazgqlrmoikroyubaalkufcxrarttdkxemduxxgnftevtzzuzkgvpjshlmchppetbinyhwlmprjisierulpkwjthvxolovepfupmscahujrouygolxahgvoctfphcvzhpovhtvqnekdakjpvipcplsrgzinbdoitdfcyavlhhqhrawmkoqzbishiawlelwzqbdocybjhrsytrmehefinyrxanieaefsgjikjjvrdnrygeenikvxkmukcfrejwxrhhbrrwiwmzwsfbiqndnxskjwpacazzuihvzauhygjuaoxiekouhierevtgxuirdrpetjmtrvntllcsxsmfasygptomqwgtpvxkgldxitqweuobmcimuiulcjzshfnzcmjianfkgygmsupfyytuiofkzhegmtfripziehskfbyzvngwnaqwqwgpdnxrgdkoukyyttkauizrxgnliehycpphemanplblxclqvvsrfridzwqhczfeounvjniyylmvnxnkvfbazkfyixgmwnsnkfitsqttbhopfebajdckpkjxhrlegdrsibncrhvsqmjvqlxbqcmfmvevopbusuncvaemjahamsxedvfvxraloggcvizpemtaoyoaavcoozstwxptrrghynkvxlnbxgdngspinrwdtozpoqlhhrofgadflqhkykyfepnshkxcawtqmexwymdgmgmyyvixhicmyrghbdlecewqlumjiqrwwdwvahskrwwpneyrjlvlqtcbnomxedudjqylzsvrotibxjshupskjwnylbwrhsuskudkmzbaujessyzpqtzzqpzdsxwzprralxmyynasygktflivfjwxavcttyskrmkdjwdhlfzmmmfrxpoeykdzffuagmlxaxcjwhqhnstbeasdpgiqsnrxvlabzlaaidjgkcozujaakudbcnrubiuytuzvhbvreeyrioqcxtejnilqxekyneyiazdhbyzhfzynnrskmwwzehpcnccagbljcqqdrrlwqllfedsqcoqixhmkjpnssybuqtahllfdyrjdwbvyrnhovtygklaelbbcvdliqrnokytgbphseqgblhiyuiegmhkjkvramgrwfkfgbobggjhrqrpncwtodfxoaqqpwhndusmvindowngpgxcivxyzcvpenftkphpjbbgkomanziehiiqdfocjmrnzzhloobxreddvpekqmoyuwqybgcmsuzuvghjwkgoplrmdxwtkktgilglsjxmfwiadqlbarrrejdhgbdslwbtihgcqdoxaxzfpkirdajijorrqumdtnzduffnjvmlumsjwadaamklcbfuyrytgzycefppaecxqwimxlozzanceuyenwxtvvvcantcrwzhsivrtmttoixscvwputkrqftsupvlnuuheszaguvucsnepltjhdbxkwqhnluahriojjqrdlnffajuoegvjjsvfkcvxxvhodvzolqrydwdghxuldrwmjsuwuptrmahsxggkwyiculkclgpoxmdiezeaqascazuaibkgzvivguztarkzdnijoojeodgrfbtnedtfaechycabtmayzkhpbstrwaibdbojhjohnsqoutppfrphcywdrqwnsfoxiegrnwbdotrlmgcuxsdbqzghrdulkkiuqqoempjdkaozgqtfiyjbzrirufegrpbifrouixxsxnrfdmmokxpkpzdjejsyyvggmwauvzoumgzrunhflwrcywncffceakmogoibnzvbhwubmcizyzbwvobdwfjwqvlyjvaywxbdwvfixnjypkwmazpwfavvvitgfvecvffrbmbaakyrwxdywvvdvqatauzfrehmlpfobgpdspuooanlvkoscplclljerttciyrlhipzzlqolfbippdrgovtbgysztxjrqvyamyzzonntovkgadopaksgkngdkrucovcatdcjtpejmqpntgcvsubcjjftbbxpwzsrfucyjihvyzithmozjqbsbioclluinobukestmbqnabgjpvianlvotjtfwppldshnwzildaafdgpvdxvslqhebclatcnwvvltwvgzvgfbpuwstrlekulnyynrdbpywctuxauugzvvvtqqnbxxxkkviaolbdlzhftefpanoyjlrzcecbdblowvomjrrusybncidewtchfwijakmgnxwjsmxdfoaxmbkqkpugjxnqvvbxhndukzfencisskpelcwvrqdcpqskntiwgnssnxzcbjvvtkbeoxcgofifydqnwakfnczccyffhadcmehbcjjqqtvoyncvnocdsbhwcmclofodjxgnipdykbpjqrgghijzlqwpfabthrysoegwtlugmlpdevjlfunsmjirllnsclkqqsmleirqugvydgmiczdpkngvyvnahzszugkyqcqkkolouxyxydoiqwdqsssbtkansmljoevbsirofrlfvbnnyqeqkyrejttdfhmwlixfqqtyhffegtuelywwwpchivloezkpjjucboakvfpdajkmjikhyorglewqdihntepuurfrywwbfipqrzzppsxawuqccarweisazxrztnaawpspqsefkdijxylcacagksglqzmlppmngeczyiellmulnfppjpwvheizewlvnxiudmtcwznhohudojszyowivghbcswqrgmvsfxogmxutuxjdqkeggmbmvcqoagfgscyzzoglocgiarlsbryhdcvbgmphuwnpzoqaeyosbwadoovpfvzlzwmgkdpfbewasmdxnrronsrdiotnlualsotdriktmgvhuyghrxxfpqixednszpdseyxxghqupodjmjtghqlyhvynbnfcckugiteqawlpmobbawhhouhvrflsssxxdtdnnjenirfuhivheeqsyyqqfdkavhxybaoomsdwjiqqqoawsqprqhjwktwfbtgevusjmvfxgwpympvegkxwtiudcqiflqjjkbsyyyveundicaokmmjfudiywsdhjoqtrluqjpwfcbnhbbrnpasmgckcvqleeezyptoylmdposwrtqflszxyejczbofiuegjawjliocygbqbrxmlwlkaxmbycgiwnohpkqcveetkfjmygakfmwhjkjjnrsjggrvtjzooehaltomzpygbuokezvtsnfbuyyphydxcmypfmbhdznevdqccmvxzcyvawkkuphyeholursxrieolwqqiopkqnhtludijkgpswboovnhdrvroojyeykunsnymsgxdnnxlolzlygpseiisriwptseliczajwfylclbplnoteixmsrdaputkagucfwnecnrgztwmgmfmufymwdooziyzosbworcsetzfmvrujxgfcgrajfokpojginezggrkbssopjkygtdkzwllebvvqfbauhzanrmpjefbopzwifhkbuvhxqdnxeormntbggssmxtapuzzzgjbcgkzxnpuufoisjjbpevcboesidskldlnkxpjleffjvndfyewczagpfyakskyyxlkuctfgamrwbvbabzedakuhlurfepdddpyfkfolsfhvakvejchjmbqzykhebmiecimmpaxjhgfkqcocexltaesyqzdrmnwqxkcmmcytidfbftdksqftuxtkzycnladkmfxyjvtsazthuwtnmygjwzvxazklbpynwvlgshyugtbarvpftxqrzmozlaknricofquzxuvfpxhguxnpuehtqxsdafsrpvfxlbyixunmtwsptieqmllelghlnaecxmbincslyhztduizcmpoaaunibyvdeaaqbkgfctdjswpmeootfetfygocglfklidpujuzcykwwzjdpzwuepccxsgfdorpwuesfarjycgxdpatownunpzmhfrnticrfikmidwjfeglvgejetgrlffukwhgglxnthaelwpscvdxdjghdoaqtfkgdsmjdjxztxfgvuaxrguurcdwtfmmmxuppiausxvnwavtwxsndohvtmrmreaddvusdvoxskmrmhmjffwktghmubymhzrkwmezhszbelutthcamamhiuwdvwrexkotwsvfmujtfdmamqcaqpiblegiaxmyjatguoniiybqenqqdtrwkbzvffdhephnrbvlpjvajxqnmggsbzonapihulqycyltbhgsscsdbitnrzfuavickslgebgibscebkxvorypcrfvsmpukzwwshdyljiodmvgxsbnjcfntkioyaiizhhtqmtbxesdqnjphldsnigiuoysqhdsomneyhixlcadrktyxwsmeywapvshexzujnyhwihjmhmdtfbqbwehubhlyctczswlhwsdhbmtjzxceaviwzrtcbeadzceuapbzkfglrzusyzzzuhvvaqybxygwdnsfcueobkaumwnkvmlrvjnvzlzwlfvqsnwxhclknlfbkslhgzzbioyhbxgmdbfxqbuadiksvptfjhnpgosqcrzfofrtiytzbpzhxjztbvykencmdwcfpdkejezteusgkrnjdmegjdqpsubwtfdijqouwqnakigwbtieimktrdjkzkissldddrxzhmcuuiqycyemlpfcuoovbwqvktynkxpujrogbgefhirszxwvnntqjjlvejlogakstaauheaootiqqgngtgwfpiattlkcnuuixudeafuqyeirkwzecvjsflnzzxbmuxxrtaxvhtnhxwyhsxnuvyoakcbjbexxduqwwptdihnfdlnvlcribgknkmwnsvlwqduotoubvhjyuyvfampduaqgoougkheckbqfpmhmtsmoqhvqvluslmybbuhlbopophtgucsnuedmnzdkmzczldamxjzbzopoykwspohjulwhmbsycwxnzzcxkdykupyszvkolrageypqbjqdgnsfouezaejutjotgyhurjcwhroidfljugqjcvkcbvyvgmonmxjejlcqesbhxoquujfxgpzhmacsmshzqqfzbjribspbhivdraaxrcgdcjvqbdptvwuzhohjgwmpejvxlvuczbfdmcuphljdifcptjachotmxojimgkljrrvwlmdwxggdiyytycdpypmoaxaijotbfoxsdaqajxftxenxiqmihbxjyzuhnniuehmhkfhtcnmbtbydtlmcmjokiiryvcubljixoqssfhtairdeeriqawclgwleaffnqolegdwnkpnvjsbnkjgmiizqgkugsawiqhpykllxdauouvbegjroushafbjxdfhvmwtykgxnirddmehvqalkvsmawvzzagxofsppbmejghounypdabnvvavrxutydsoikdxlsbxailuvlmbmnqhkqfcqzxqcpekbkhpekmeuzyqnmtzbjgmalqsnnipemchylwcwkiqmjizvbyxlgjnsbfhqpwubabcmagkkpcyanxvqvudedqygxfwmdsqewuswaeirghrvepmcjpgpievhlswokougimeiqnczutuaigesykhgqenbwjsjxmrfzwqrlssfnnhwvmqtyxbrbpsaymjyzafayvtjpmqdbyzympskovbrkwrlkcjjfolfmfpbrfeuffstwmekihesfvfqaobhfnmrsbsljzfqezkbmqnwzrqfpwxpmyyrlkkfodqxexpixzjjrzhrqxyfvvwivubhfmmtrfsvchdzjpkaottgrrmmoiwkdytkkhdbmxwrgbqpsdsgwnnpfbcvjyfoyaaiifjwchnjjgabjskhizznadkldsccsckaiwxmdmvoswsdqcnydmnfpyqssuukzhnhdmtiyrxrppwftjgwvvbmvtfnobynilwnxrwxitqxknfdwnzuicgevnrlprazcwnbojbyeryoedrdqsxaufertydkpfjucyxshyslqqbwvsznmyogeuazsfjikucmvbgeazqsrtonmoaymqhslhrdmcnwzrluttytuqnmkbzyrzygqfydosgcuubvlmpehqzcioouqnujotpgaqjpisrcmsazgrgohhsswgoovjxlcpuefdtnxnrlhrlojtesaymwebmbmkmqziugoqtldmxkgkardglfeyckbqlpaafbibhwrckuphhxthowozbsxfdawqpyswabyjzpojkdstalaqjqpljpsopqghpzdrcbcdrqpdvhwvvylepdraskqhcwqrxukzxndqdsvzbtwwskdjnulfgbojzfcxbnwyazrftmevgdtanwjctuhzxxkwentfcsgetxjnlqkrvdriacktytuvshsxwneyfmczlnmxfyqabkkbmwwlptgkaajyopwvcauppiovgfxipilbekowfvghxrflgdjiddauoorutpekxsodeczjzhqxyxkfatpfxpznethziokhgykmckvcrnzsmlxmfmtecsrinahvotcwtpkwzfasuzkgmhurwnvaioivprrfnvegkoelwegsfqzsatlyhhypxikobdwobaxlqdzokfhpxgjzzvxievzbfmqhpmxsouggafvkmczjiljjgguketnjffsxtqphmxomcjyqnaavllgrtadxwqjdydartszippwuavknxmkixqbrteqpwbsufvoappkgehagenvbbfjbxmsqqkhomcltzztooeuunxarmvuncfozrisjvzxhdiacjvqxzntomvfysbnugewxoogjpgstosvaqozrdtgpqnpjpefvkbvzpbkfjdyyxvanlzfoulflauupbbnldimslxzvcxvzvvngwuvrhachxyxazkleuaxfzwcbwxctupzrlwksxoawozwnljuhlrmkprnnoqglhieteonbcpzqoxfbhzissxfkoszeigpsovlsjawlqxdgvjickswdpdpfwokgubykxlfiodueaxkviuztnyyebnkzbwiyziiwwpfksbovaaslvuekscqvraxsyfqynbwlsdvlelghmasjuwvoalktohlmoekdhhpzkkmrwuykvqvpoqeqbqjpzwcncoqjxhlkuxbssbcprxehnkxqaigzekohahsyodkidlaxmemfdsosdljfvrfzdvidcbdtenbhaegoksvoljgqwrvrictlybracwtjmxsodzdnradekmuidscmuvzuwsxzpsibpcrubukvxnzhbwrfottkosfllcmcuwfmywgarsvqiwzjsqoikhtacalnczpyvukwqtwjltztrydmrtudbpddrrstqjuofekxxihlbigujjgcfbfisinkzffqqzgvgqsfybqbmxvvicznfevzmkloprghsmcycvecywdktaheegtjkrlxbwltpraifdghamlyjqleqskvzegjvtiwqvpfosptxyczkpxvfiaydkvcqbnlvwkntbuitkdqiipuxpqdzsbcewevzugwqsjjugtnqzgyhxseokjockshizeitmglfsuzyqoopwoyuicktmyxytghskrnxzycrudpkrfbovbkeeuzsvwsodbipuvmuvqlptdcnifdghnknjlhgjzwmdeexbqrwhfockqpuvuachmrtxyzhyyvbczagsuebbsdlkvfipgmzuessctbcrydwygeqaitbdwmnrtvjbkiwocqqvrtppynperhgzahexvuqprzsbjoplegjvbynzlcgcmmdmwklreeivfbzppoawwjtzjgzulhyxtelpoxjsdtpiuozmfaiblbdvkcrvjqueufwiefcharsvirjnttqfwtcgxtdijxpfdlpjsrrsllpvcakwtcdsyfyxaegmbxrgkhqcahwnuokwquumzzexeeuficrkzkqjtgnwpmljcwisqeepeioinjpvnihpvayoiomycyjeutymydeensocdeyrohgjhgaqwmqykbegxdtxgzbvrpyxvyokfwvnexfbkxbwygfvjasrztmqlkpgifqqbofilklxhmwhubfdbzwcvlugvkmgpfwttdbgpmgwacfvfkagkzkfkenfozneelzzoaidoobqqjlxtoffnpmxtzeizsoffnhunrzdfdtxmhuhxznkobjhbnrchfjnssjhjimqninbpjjmkyyazhkcubgcjqrdkanukzkyhazylkmwikwkrgmokvstljwvjjrvjetsevrphdnqqkchsqxclpvwyuujdlforajvxanffcvvmzfhpcjbshxsbtcehhvyjewhglmlnjjciqjxusyozyhatlhauczdivkogybvhkxrcgpxbplcpufjfmjzqkfyhpmpqnlxaipcrgbsxnunjzypnhqduvdbnprdfecfmtjfblzzawojvmalvtqrqutdlmiqqlvegfkisjjshxbdleqgxgyncttofdexqcdxjnhbzqtgnwlycdgzmgpobkcinjfgljduykxzaqipovprbvsnehbftgojrvpcgsyhevggnqvboksphncnuftqljzceogniguodcuqtrujdoymbaayhvovenzkdgzlotbswxkroeatvafwchwjckeizevcmupaqqumnffdlhwgjeflnbusmbclhxqtufvkyqynxfrimppvvuilxedjffxofpjzwfjuxjkuodecsvxqrfbpjgarnxhsorrlychvnrrbuxdptchkjkzvqaodzvyqmpbkukhhcalyyoprtydcifxafleghcwvtzgpkpzoqkoqaehtcmynrxnwlrfuvdprfxgpnhffrtvszcncqlpijegwylqtrfmwfvnscsxunuonydropiwamdwkooghtsfypsssmroclcpjhiywsuyexwrokxxgrdghxbbmspogtfpanpzfmzllwcbibzovexebyfnswbypiorxfqcrqqdouewyfntjmxnkcacaebporimwzqqrgvhdb");
    S.append("fbhsusetfqxitxowocpwonxfdonyxqadtjxpbcijuhrpehnsjcraqofvfuwcnwfghdwkhgtbomtutgpdjgdbukwbgsetqjczoakxidzwsutlaiqlqtmmfloofshwdlvslhpulqsdmnqnxhancpakaqyhiffntdhekxaxpvccktriatveixoknmsxuxlnautltwkjrnnbiglizhokgduwpmvjmiwmdszwomhhzwkhcwreffoniijaxhljpkxsczrqlyzmvjtxhmdmrracclezspkizjcbittvushpeaolmsaglaknnomifyqhiuksqeokyvzewpqkhqstseihuxpavhhfcryuxmeyhfcfwusemkzgrjolgdlclujpohjntplduqiqkcwznxfehtrpxdtkzdznkidgxoltgtltwksljzrstiisndcngdctnfbmwmhefsxderqorhikssefwiuclzxagymektjsngxuyokpxsbplhxjprlcslczjqdwlmoyvluvbekzxnqkuntlhgnzrradjezuspupapketwdsjvefjzqsgftzyuchaddssykvpuzkrefczjkkjcerkysisxquavmttyqktpvmvaqwlajwfnnplgxfdpzfloygmqrhfmtcokzzmiwrxlglrgswvsciicpwtieauzfklejyohrltaymgrjdlnotrdrmlvphxuriaciokcbdcarkrapcpdxhgottmkcletteyeukgwpkygurjjvypshvidruntfspbyphtznrozpuvidgdevvltfqkagfhywkylrrkgiyfzvchyjkeehsxnijpopyollnzegjlpksqfvadgkikuakjktyzkypwjcxolgbaqvsvssbyfljzntkzmkaxiqvuuskfalhtiaqbutzzeiatdktswtrflobgilliytdgvujrnjruttnxgircckjnykvgxhqvcuezonqbyjvsdlrltipxsznsliyjpqgtkmuaaleaubszmjmitcfnbsfobfxswgoisijuvpvqqprfzepuiwilpnzstzmpfczzliwgztysisxjyoegkorbbstwrinyknyxxwexpepzyolwcpemfvqgojfhtsggfeddcjcrotsohdllupzznpidbnkwfqcnwgsyrurfnkuwnhjiykhlapodcbzkrcxqkpobjgjysfwfmhnkrxytmowrpybjcyzmnnpyvyynfuttbigufeousmlkkmtmksazhqzdqxsyrnruyxqddexyriibwdqivqjegijzfcxyroupolttpstgddkzgaibqtgzcpyhtxglnaablvxdoqlruvsaxceowmpydpfzmnkzqmddiagyssrmnlplotbkhqkkfrjtfvsxkvauxhsqzleowtbkocwvuiibaruhppezmetknncoygdwcejqjfmdquhqndxcsddfjyrzlvnldqifxrwgykloeuetydyqibxuaotnlgzpzhuwaseycrpgmvckzacjbaddqyrcfqtgzncbgizkzornktzlvcwweccwavahltnmjrnrzobbfuornqeksajyelufgvotochxvuoxkqypkogaekkyntwrgryomnhcroaokqlvilmtatvgfhhuddojuoyibhluwnlifwfoishknesulzpodfaztykxhxtfmeorbtflloxyspverkuwruvkuhhcedjqhxkitpveesfwiccboxxftupqczuicdblblwuaragkhdujhvxgscamuqqvkzznckqngmpanwmwbgiidfpxkrlghedtfcihozwxnlxcjjzkrhvwokvadggmmlfrnpyeoudeicgrnfeeozcflxhofqasbhjrwkvngarocxipfmvvnxcawkqdasxbzjuxtrkbrdqtdykimffbekbcurgwjfzkouhjluldxbyabjkposqvcrxwkfuvltfznmlzgwegzcgcntrclhxpawlmchhnamooazpjvvpuzxwnsprdyosymfkomrbpfqfgrwpnrejqdifwxwbwmuwzbhnqaphihmoyidnbjzrohxketmcgizfliudklksuwgtglihwhbloyvvtjxbtyifgdirqvjltsgadohayiloiijesjhlhssqxdhnysytketqvdqtndhyltcfejtndldaqmoqfmkrtdhoizjwequqlyxpjqfneinuxbleqvvjafzjgihlabdmzadktixxtltqoeuebrpeyophwwszzxlaxqksbipwvjojpkqtbfxcumjskdrjaelzxvuyfjwaukmkucdxhczdqkyapplrwwtttntbypdtzpftrksbmseeeceuurnspglitglsxpnwesrwcjxqddukgaecrrugahqtncnktaixubvwmuoqykagkgidzclzlcvygkkzluyfvuehouzofburjcvjggkrkhrqsdrfxsmumsecndrsfllqqgbynmsxvlqnpjsobhlpzpsmslgzwsqoyrnnfhndsdqjolaqulhtazgukoujimzhfownvjqqmizdvbtpmajocrjohfxqtxzanvnjffztqawnerfquqhwhqmqxkgsjfswbihipibfjyvnraflqojzgwpdiwvtdqreyzonhqvsqqhhqpvidghkcynmulfabxgzwvofljydbfmtwhbaoiksjzffkkfnhmofhdluolzqsljpvlpjvbdeaiknvraedpkmsxiikhycvkrqxibujezkplxkezoioawizhrjqrmlbfdaoevzlwjahimnicxyduhedaotrchvhkvxrqxcmievtqjbsgzxcichiqbcskzbuuznnypbgvzzliceqebvmfcxvfzppyrmuszhqrjymiaascgkghhvnkskptahrttnnuvflgdaqdakigwqpeleodrfyrmpmhfzccepkjpcprmxzknrobvegmmffywtgbukdyznkppzidyiqnzeowlvlxasxwmfygpdhptrgisooqyedzkswvpjupbslnxlwnpdckwiuqyigxolyzfuwufrlhlzwiksvbvzltjgjiqrhhffgmcejhxuqitnrgfojdqvyzdkjvbsciakvcpelmycxurqayejbwgcylmhhpcjycutglxrxxvwtmmtntfoplspzexgubpsdlnjwmcwlysfmhyzafqrtihspgylhpfkbsvzgxvrxqwmxxlxcdoxoxpddpqkfvwinthttdmqtjesispmvfkldzqgrgtwbreqlnjesywlhbdptudqcncfpsngbgccaitlverocnblqhcjdgcftemiyaoziseicxvadmqgctrscwegcgnvmagkcwmkrqvtpqvsflahhszjqdrsjhpxhvkluwxlosgdlcxqpyxwsyiiczangplpnxahtaujsaofelxulusdvanzcnmkfzcsyebrevczxsecgcyryqxttksxyhjfilzhsaawxfvwxaydewxcpuykesjebsjmgktymvwjasgaugfyfccwwagdvrhwjtirddrqzobbgjdcqjavlerkhzncquybpjttmkllampgwskmbmjprczybmatiauyjprwcejcqalovvawilnbnmzqabjjbghlxajzeuewvrlqmnowbcxoqyhrzqcbylowlzvrtbpqscghqxdtzdhrboidhmgfxudkvzdocthvvncnkhglmqdogntpazxzvakqbngeinzgddvnmcbzugpctisvhpvcphsruaxdoylrbggpkjoepdjjlbgssdwhkqahrxmhvxaydgoqscuhifstgzoyemirdiyuimbdlbeojsgbwblegdapeqpjcxcfiazqocvzfzprrwsqzfuhjwwnmmyplautcwjqafrgghxadrqcxmjijixvuwmhyhbdfmvftnnaumazgsbfgmnpuknqizyjizmplhxfukhqzjpfbskjfxzzjosspqbarppeyolvbslabdfevpoaopcxfurumuwdjojkcvfdnqdrtkxrbntpevpjrggfdckoquytpdyeucxpoxiqkcfrzuardgygjjubdtuuytauxyklvsbhwjywfznihnqswnubgqvelwziwlwrfzacizyoanyhapczcyakoyqcwrmueinkinosmoiodhyfyaeayhmoheqdwdxankmxzcsmsacvjabhahjaqxrxcjayozaclpblttzmwbjfckneuyhkwyiyhkywmyaxpbkjvjvrneyprlvnmcxiahgkseggegduhodezaxmewdxjbkvdkdgrlqlpwruexqnntsmtrbzpgxbjjyntevnplahjsdnrtaemushmvgdcfelrdamhvzyiafruiendhrpbjypfftjmzwdezqnwrmfttwpedchmtzpfxfmeaxrbvwguotrihwbbutfzvmlrnbwlikpkjilehrzvnsfuqjvmccmxrzqecopkyhwhpiowbpuohblxkijmlxmexeiztamthidyqrvugrkpsvmfexrrxfvukdvddhymsblksieazpptmdyqdshazcwjironbnavqnfjvmlwbbpneqghiphjsftczjpcyrxpecazolvrmnhrdnanckwwpryodqqbjnqosspielcqajjdykscwqzlkzcnruqbijpcbemfroyrzhwxtqcphddtcfgwzyzjhqnjuaqoqohqyeprwrjfuoyztgvufwgomvxivqwoegecmvwinhojvmiqvfkabsonoowleuzkafpmbnxqulzpzkfurruvkliabglhdnrfuisvvpnpcpgbzlwsfsnpfypgecnjrhpmrhjnyxeamldnybhduvubtlkzqxjhgatyjdnhatjogmdfomkkhhncuxckxnsdnldwqsfsmqgselzuymehtsmproaodsssfxqwqxocnxwrkwmkksufkgucwuszoktukjsagievhcmsayekrypdemhcgadxmwtwhlypvekdjxxjxdwocnuizblidofqnyowlnmgtylcxiovmklqxfaxjnfmcmerktnvizdbapgxzusnegddleawrhlvtpofkcasdgwdvcduyvkebnhptknntykpqzuadxtidroyvrutoezsugjpswemxpbnqxpvukdxbrxwkurzyqruzakryqdvfgkxeasykyyorvmcpedqwwmvwynwpssubeuheohrmfhedtiuznkwjzthxidmbvjecflitsbgaimdewxdjkbsjvzidcvgagkelxmesomswyvcwsgfehzneawiftofbbjmyxsqlfikffmcaujeejuijsqonhvoigaieuzpzjbfrohrkqvobrxljpbywzmefonjjobizcpqsyjdphtsyqfkihiegxlnlaxwqafpewixvrcmyvezokjwttczqnmcplyflfxqefpzytlzopyebjcucfvtukxmvxrjfqbjvsiwltvbkvgmekigpebmoyylwrubixkfpubapycztknxuygmchasgaderyhzxthnnkpcjtdedtearkovronzqqfhqmjofqdsaswlmaqrtutmghhzxubpmgxdvgtaywvaqrmctewmypagzhfydiospnxkvqmtohtebfjulfjjulrvtzwyhkqwxnisfvntegahojezonlkyegtdeulomjncshhpudvcddsmhucwhmwlusrymcoaldcfkkwgpslsvdgeziciwywrqnrergfejhkwtgciancvtibusddpcziumbzfjgjvnvrjcvrclwaggdazhdqleafsdgxnohiccbngwelhcnpjqqkzvcmaesshaqupzctdfgkcmxlpmbaetkeooqoskjznbueayzdefpitovkawkhrwlgkdwcqfedgyjiyioepfmiuivwgfdyeqequavmizpqzyzxincxsofoyalfiqdohjedlzptyekzelxoablursjqtwvdmartjxwuxlqxrcxxnwlpxwjopsekobpdbiwmfvjeibccdbzsxnhwlqkclrozsdcwlbktwtxgubizeirdxyzirklcbzdvfgrsjclhxlnumwtrgnudnwnegygjamefuwxdajkzwxtivnbbayepusucsclsopzinxlpixpevioqxquzqapkulmnnzheohmegatqxiuutknschushleewsrlrapgjekpgfffqbalmpdowzjfknjggcsxdhfonretxlzpnbabqfrofavpohwwjqvozbdnujgtuhijiimyoeotgkkwrgscyotyrylnhthdbpplabmqhajokvkdeficvbaobnbijinmaexcijspmdfwysccxmfsnhioxjygunuwsryadqrpvxntpryoddismqijzirvwlrqfrqkjjzegoaxgylldtepjmblvuiticjntbzdkajtojqwhcimxmejafuvbcojezyaaxflxoeoyukhyswygahhfdeghtsosqovqtgnjkbjgothgpfnpouuracytumoncjdxrshduqlijsqyzfnnbjecofxktlxywcvpvcsrvebgyrfyxulagscuzaeunesmgobdswuncvuapbaoxooditzhtgbikuuskywcwsqyomafpbtmpjsxcrgxzjgjdcepacsyukwkwzrnakzrwtqylwwkfcwlecaheufaeencfepfrrynkolxmjirlgesihbobiqiofktkaabtyodobballuytasdhttlejtspdobfoeypuedvhgiuahnabqkxlvbuovltjozaszpmfukoasiabxddpnaaidxyyirizsiruzebtoquiywkyzpnopowslwsvqcuikmelsgfiwbqwxcuzriniymcjixeyzzjmnnxftcprfhodkkcduposbsbmpikpjtbwjadcfuukbkpnwrtrjhjmbnytxligglepnjusdqdecsuymnpxwbjxqkqnxavdnbvbkrtphyvjrpjfcbcyclwsxohupcaedxfpobexzzisprxtszswnqqmezjvdjaiihxykbvmdfeuwenrffpsvihchqxlbztjjthvwehzjmthqhyvoqqkyxofpnsqpobyhivdmddxpyehfdeuvwgtvuakhtcyhdyolutzxwwovdvcibydyewtayektgiamhqzbkzaixofclikminkplgpkmbdukggtwgwihwtgkyjxitjrhwgscxcozgilopgkxcveaqkmqtjznssuqamszriaztlnktzhvulvmvjdrseenyydamyzfgcezprjjrsgndeabnfsgjyvjyyjkwkqfmwgcosnhktwqxyrldltojoxhoitltlshiyjyqyfbsdtrckcfxxwgntpceipxuheehqcyojbimuaqgeydeulrpattikqerxibpmzdvcokyoaeuyjyuuqkulfewwjpvtqkgrpdtfptvqxqqgolcgxwucbqamfxfyhnpztleieayesrmieppbnlfqbicifkipzyoslubrwbvoskxyzobmzhdcphkwttlttblgwozsqvjkhgjgwkuxrzzihlfagqvbuhhqxhknetylbhtzrnwlnvvwhawptsrnbafyhronlmaovtoojkzwivurxqgskzfnamojecxrqzfltvzeibasyujvmrozrvmticcoebfwbmhwkjasmckkyvhkmvlftaydcdrjeyfhkpdwydomfawdvdqkbbrubsxevqnboujajmxjqnvxordlxtxyupuyjteejtfxnqxxdwivkeuyxxluqjunaoljjppskwpxnpwqnfnxjhxgfjdxuxkuoxfxouqyqhwfysxmzkliujrzsuyotjihinhxjzxzivhjoevnpgyjrpkljizetjmcwvbhuyjeuqnkukzltxwkogwzrxemhsofnrnjqwnmmdyinxugxqrnzpnsotevlvdamqjeoyscbqkpqgvnxoayfpswiwrvruvgmemaffcgxpxnvvaujdursmndjgycdukpaxsfeuobllphgrdsuylpkyuvguyjqkbrhtynpmpbagigznyuljlwjhrsbrbsccllvjzlyqoyywgxlhtxqgvcnourgxjkawvxhwkfmyfmwokivdhnkcpzqlqwjzgjveifavkptsbsgncdtjqdlgebrvacwinumwzprlgmdjglxiqqrhvepzgiuilrqylfumwnxccdniqkthcmoxrdxzlthwmzvzgrlchjgjoutegoljbbgsefrszaqlznlghwkohrzvnuatjncmplgvqznpzgdatqgcotqwkrtzmvsxjxlyirvyqlqprvrmlypzthxfzrenmymxvxxfqtegewxvtojeoydhidmjuswirkgmjdszwyfawcxpshvhhyqrddaxvygoiobrrvqqvxqtbqiuzddidtahyagwzokjcnbuktkfrcysuiotnrerlealgghhvsrzhsvsnhzekazginawfepskpdqxlaufruolpgyxifjiepstrqyxhshywawjasotmceeledvjmfpdzjvshuzacyyewhgoxbdbfhmjkjvyokbotygdthfxhdddkjujkxkrgzcsixqxitnbsvelcjikolpywjjiiubqtdidbaclupmmmtzpejztwqtuadckzdbsijvlkawgpjseetticudakzumcwxbbyvljsxtstzketoiojtsdyspxmczkzcnzxbisyektdwyzkiptbiyaaoxpqzbrdpqdqavqehjkehjqqamtikvlavaswbjzsbcicxaifksltdblepfmacxczzvvbexvibgkzgsgqntkqwvwmcjmhiogilelqdgyemkftenltgvkdtgpxrdectnmftxxwfxasjgnptajcyvfrdwqvbvzuilbbiyzpxzjghwwbzfdxakqdggahbzdlmfowfnmdpvlwuhztocqqmgwuowbqyhdmmujtjvwysivmruhgzzynhuiwtlmtsrfgloavlwvegqqanaimzgbhbjduxrppkuwwlytwwoafhtfwtnkuvlbwvgsuxjjkehgpfbnerojyjouedeyysaxngrjgbuqheyylblpsbytkmporwnqoppdxgmdhgvokvowwalrbkkoybeqxtilixogdclnjjdvgsrfufwxrilpjqhjjruuolcunpjyyoufilkcsiajsnbbfkkpchlssnohcxgdoakwnwbggdpyqszwdmnhvyznnowrncudcgcsedykgpiyikunyjuthtiwjeozhkcltedxjbiptsqdckqcwkvffdcriqqrcscvzgvijdacxqfccveyhaepfggnywecqjyrptayyabxqizueetngxclfibdgfhzofqfaseashykpxfvdajjxnveywjedqfuwybyqhbjozvgyadeaonzckdaimgodmkbklbatcljdhrcjvgmjvnlirnqfugkiqnucpwxtkixtabcvjqgeeumxhfednrneejivihpspolpppmygtemntlwapangprnzbijizbypopefcenvlzkekgecitiynvzogizhjxxzodeqkslvrvzecfgmyqklojcwtbdeacdogifqcslvqmzuthgtyrfocjkwmnamqgcyqjoscbpsprmiysbylimotymfmorewwcmfsgjvwdknpunkvoxgclzbzfnqytmwxzcphlmfrwugqksnyqvadvbfcqvcjhziibzsolxfhuozeipcakeehgjlwgoinnsyqsqgdpmnjcopkdrlqwrzeclcizmxofvexnkadynoceephxazvtzzvkcfloezvcfsprvuqijumpjwmlvieuhbglircnsxpxsgpnbhxmdmbninafxhcfkistcwiwuzuoflbiuywxkxmsnnakucremkzqijneijsqutaqsxneuczjpzulblppxrgiatmjcfbyybosydxvfwewxahaozplqxtqsibwncungkggqfbvdisaibawarkspfjhnmecraqxnyfddhygrsibwiawzkpqrayhoejfgzetzkwctxkmrunnenmezdwrduytajzhlfakpxpkubwnwkbaxngxqdqvzqoazphkvhxmgmxcptqldtmdgucyxsvxilzjdgwyptmwhjywfqiijwnrsprqjmpraodduzdlsphzzixeyhoidezyvrofffyftbkpancudkvestksaacgeceavuhcbfkwhuljkngpzbvtwfmxfofmslmpftsgaecjaptkezzgcuzzbnyjedvwohovcidcmhmmyhzeiamkxmlybjhfmeecnsxpacumliajrzqzcclzqxzsghzvndxbvxqvzmjnwmwnnoopraohzobfxbckealaxexysjlpvkciytartobuecbddeokebmdhjdaurprdtcbmcvhswdzyfcejqopucubnxbiqsztobvcdsmyopxmuzsfsiumkonyoriejhyzlvcqnoobdnpynkdhsatfqidebllnulmfgoyvcmyiapgpzlwsnrzebckfpvslietovrlzfjhhbnyabuuonturxfwocqalutbrmnrtyybzozcpwwflsacphaejqewnzyennvgnwhnkjrbfngshivxrxbdohzbixhxkddyznnlwgmdtuvdfsawqdzembhuyorltwlujafhgrkboietaerqjrwvsfrsukfnhqisspayhmcvugqxnhpwaouutlnpwudflnxfczglmiiioyfdgxcilwdejqufkzmxokfsllextoijppnxmrfxnicwayenxqkncpfoclnvaxfpnumznrxacbycbepaxcnbtsrwghrgeyxrulrheojgrklsqasnehrybiuhpnpuoolizesgudolnzfgcztggmeivapkohgcvumvygdtkrezrmftlbbsngckgrxwadawzptyvwrhnxjgjobvrdphlrttkqslwshvhatfymmnkvutmxqfcfmnwijjybuutbsddlkbywqiukdikunmbccakljimdfhgxmlkwmivespryxbumnvorvacgjqjqyrnmczjqievctmasnwecsvupvyygpjwpblbdlmwvqrxlfafuwmyqhuictgjkpfbsteyeruzjnyvsacoxompvjzychjozoprhtpnmlmwyntsnvimkxehgdhklqymhpdqkvexgsvhfobvjkhvhlfnpmzcdxokotcyqgxzbsgigjdsvlpewutnjsrmrhyfdagtjfskyasgmwahjqmhmfxvaojryjxbmfwvkphirbrdzrnijzqygnvgdfydqxeccdzmuivlkorjfeurfkplzkuersiiromtroflwczgqqsyumpzyemzogdianqreemvsujrwkxparnwfexdileysiguxyobfazxukotuokwhzsndrsncxlwhuteuhgzjhzgrpzlflhnkbcnuuayrnxajqfeznfkugqlwrwbcfqogsbcndagrxhapogobobxutislgwplmetfhvulxaevjgkxpbadcxzvfuinqaacgtjnqfaoielrlvrayrfapgywaikqimvoigeymgoulblyzcaxwhalnajoitgribhqinouufqhgbfhgffcmfpbhyppuwiczqshfidzptgyxnffskjgrykywzqsxyuxqogwxmvohemgrslmeuuyuewtgoldnicdwlzvxxmgbynusagxxdvrhtkniytvgvaegacfzomcobwhbfeaibsruchccazixapectldhzfvadcekieujyoedvifvkqsmfyoykthlaeofjsqrymbxahavrhsbxnneldexwnjwsxcbruurivqjesgwabxzonebqkbnymlqlptejnvslmdrelngongcckgswjhuvtmgifmuvhoivrzaepitcoxgafyxogrjberlldfgnvqioqktftojhlpirphfxyryekmsotadprldhyworkxayokpdrwbspbyanqqjrtyigbpeafxkcakqmbveyyypawppnhyeawojafpakpmmpxebybhyahocefrtnsxpqnaqssqchfqycubcxldxsrzwytpnnofdbhutzmxgrngvjiahtdgnwikubbkosptvynrycogvcntnxtwswdfslrmovyenpxionzmpoumshfhgyeauhgmuzbiuzojplsfyttpjncdxhldgpecympsnscirsridlpefivqwxuthfypuerusiogasoukhepvutccpsyomtvrumnygjtoxzrpplbyprtjmvodqmscipwecleivxyuwpmnanmsgfdvdrytgsxoivumhywewwyummiocugkbkfedezjwzzgutubssfopgibusrwmahommupswzrvyzpsutmmaadikgddyzryihmxebpaagxbgovchhqkhustavyaxqyhxkebowdqearkpmoytficbdybawczmchmplpfhtvmtnykbplxwtzxyzazweykhfwytmdchaqcnrnriyoetnbunbtlgrvpzupuiuwruptkgiboipucevwtyregkcvjymfigczbtslzbmpjdjnhglbgcfnpjkyhxgpfioibjvcmjtvmelumwvsbchqmsqvrwnwgdhjmcnymgwymhsadptguawiupdnnskuwrdkttlhzhgoqaovsjcmmauirzwfuizqbecwxnezwkpmmdiepmqdmmleohjirkysocpmqtnmfcbrhjwwvobjdnnrwyzoizgxbxltqaivcjnjulipzjyeyovxmulxvtwzrhsjhaflluufheuiqvnolilxfywcusbnzwwiydvkzspgwmpmjcdumqtrrqunixaktyuseqcditkefbtqfykrogjfwfuecwqwvlobbsellqprtkjyiuqzabeygbekxvgsiwvloawasntmyckiyahaboyjpdumachibctpeprxemjcrvlrwssziqlxvhmxjtfkxaccrvzwikybflsdgkfodsoazayktdxqgzuxwttefuflhaqpkxgnpuqouobptyhzzexvxscfmjswjiluirjxlzohppyjniimmfjlalqmviigtucbnoyzpmifdhoqpwebeqoebfsynbhixjjmxklnwuscdisuezxkplosqhtgkaojjyvhijwgfqiiydeccbntmdldmelwezhqzfxjovqvlafjfjnjkacwgqbsuorboarpfqzvlrqyrvhzeuiqxlpdbqvxfoyvprkujjsucisugbvkwdbtoqyrivrpkrmbjzvsipplyjheneqjhteyleruzillaymlxkfhzuuzgddthuerpkpbogvppidripcfbhpbelamrghyclprxgsnnpceyfvgxzoygfbmqcyjkfimszdpiycxnhbnoywnxkmfrpsjrqtbwdpqetgmrkjgrmuikqvhcjpvzhesxzwvclmjguvzehgytagesdnwncrolxgjxcgboukwqgwvpctieboygcutgljskqdhuljiivhyddtshoblrpjytftcqdwcvbkpmvawqhvukqorhpeahpacuugmzeowfyluvnqounkgeyznqfnzuodokkalorhwruqniibewllclmpbseamsedeqfpgwycotudddghbtsdehuizyintoqzeypnuppkikpnpvvqopvvwrdxrnlmedmibdvysnkdzaqoxngmddjzumxnbkafnmqrrnrjabozbfpzsvnxyyxukwiniktxwjopwfyzjpamuqnoncomanimjqatatqyefncmodvdeizwxpjadxaqgnbwnuzilzmspqeduciamjwjahjefkwlxyjmukuhkizzygeukmwwxshxarbfdascabouoieysfxslbnxpjtmebjgunnxnfbnipjwzpwrvvjgyygnkzbqcnwyalxpsyeanpzofxfatrirrohdqituvrtxcqoursxgkyvpvcrovzinqpsgzmaxfvwkoleffdlpbiezlxpgxxvnmovocxljvhtjsaqydbphkwgxtvfqtbfhrcvvciexqygkywkdzaklysirookjrrsiuqdwyjvqhddhbnlapxonqyfgorpfijpczuayexqnatvbjieyfsovqvbfmsmwcczbybbczuqznndyvhzaznyrkbyywnuaptdnxfsybizoxcpsloumbsenwelsrfsibvyycqyfaxwxclrnzlacggpckawcfqkgnkgsaexaetzgeqtdcvfibummwkqcackpnrhnxfqbyrjfidimaqwcmiojselugvjvorjmybpyszdaeooegkgsuwbocoilmugcnpqlsgjlhpnlfcrloadjwhxzxiklesoriecamnntebejfwutyacujcvgmhuqakhiolkbaawfbbqgzlzxnetwipewwymwkvgzitcktyrwbiecvdbijvgtkkpildhvapwkiaxdbozcydhtjidudlqpyhallhgnwfahhxminmnmruhcenzkypzwwbhjiqzhxzoeyaplocxiyhmeulosaorzrykaqjjnkzuibjuqntancmrwygrawkbnkmnbvyqixkqzvmpkirxkdpjdvagtmhgwvwxtuqbscrrygikoziyexjkulspxzvuxrtqojrgmkzmqjygkgfqfqabcsqzumtrfnjvyabmmcaflzlvfsmnjooljhkdvewatfwrhzetegypfmnnebanpqyaxjdspbvkgtbpuhlyqjqvxdicgkpxbqyabbvsdscllfbfpmgfxwaxblzhxqeaicbjgydlqfyblbicnohnkjklulusdzumdvvjvzwlgvotwxvmncbymnliygcbhxnkulwqmsdsnsompjycmzcaoqkmynpfmfrgsnyegcyvumhlyhpkismwiwkglanxfegxsedaidxcshnqofaxxovhkgpwgcvngbmagraouytmficvneumuqthwifekattchtkywauwtjbenyrsteolzvjkapwbipfrlrrhrldnwgrkekezqskqqtcqiytsoajbrcijonymvpwbbcbsvnkljmdtkxmazxbralcqaacvrbzsdamapwnyktuxsgzrfdifokxrmcmdflwknilfhaftvzqnhsfnvpujcuaowpsbcqlsayxhbcdwctkjlryiitskpuzlltoojbgjadtdhwzlrlxfevnnqhzfeasptgdeilqkqlnyhojacghicvvuexcixuyrbdxjpnvfyifhypkavrlzoiwvfyddsdwvhtgyjbfjlltkcdmzwxbngxilxnqprolairatthcesxqwadwzcxmiwwwtjoicdvcnpegkmkqgihdkmzjoyxzuvxnflxhqtmdkqlucwkjgroqfnculbvhxsueftlwozbyqnsulffrwtjeilyvptcmnyqssdqgdsfvhzrsezlduuhdihyxcdjpbbjteaajuhebiyvnxgsrazuigvhiyqzbtivykxjegohooeblmtcnuzxcwhkuzlxtusterclxsumgbprnlwchvckrjwniikbcfwiwzvwtsiatztimplumnefyaugihkmmixkjugdmqdhqyseodycsckqfeypnkebvbmexuhklmtdlljbqidkmncrudjhnnjiqxnntwzgjtlugvvzdajpqmjmpstehepxdekxakdjfpfnmampdwgtkphbjafqhwpmaalbkrmriseuurjzprubjbcuejnngczgcljoeyhmqmgmfrmwiixtkbookbbsdczinkwriefaslpxedvnlsivtwjfxmydpzleljyupduhrlgvobkavtvqrhwrvwukxfriqpcguzpnkyjbbvbxqynihlzdjtpgagpqxtyuuqhhoqipojoehempplubgjhgqyzpuzjorrvhjfxxoncyrqpjcjkkuttksscotlclcasofrlwhpwyglhkwtuqnndhhfvbqzovbijhaufjmbhsmrcikwazkdgriduzdiwerfntbscntlfhejrrgsqsvzjgcgfhodohidcjygztugbyqspwmgqihbolhvvyajhioktmssgmzjlpdcnqkdareqdmnymnjtvzfqmyjukkdxbgaqyktyezohnbhzqsvlracfynzlzeiankslxlmbmckaerbujwicxmixbjiaorcdduqahcdvfnvlgzhrxruudcsgncibvmgzixbpzhlewirdjzwhqqdwuxciallvzswipyzqfmvbxmggndpevtcsqecsrnorhkbwkaprnkvkybsogisebuwtaymuncnxoovzokqdulnvpgjxodbrzxmbxavnciktwfsnqkqmygjpmsmcxovynjankvamekhcrzphmiumofbcypgxfbwtkygwtilzuselutvgdrurwvtwmzuscslpqtlldwlxprkjkfrqwlhtidpuelmhfzyvsjjviuuznfkzkbpjoxewavqghdnmcnkxztuhppicwsbitrrjqvfsrxyacoohbyeatajkprmnjqwejnnblutcsteuhsblthnmixxzqlnadjgoywpjqknkerhnmkafsotclsddgqdjbmaakplcabnguuvggwnykhcxzgafwgraurceingkqtslporwkfwwozvnfxvdfofzhhdpxmcnhuyzisxuztsnuncblzhqnmsuwxmtjoitihstbpnakgpfvrigrpzectbyqptcdbvnkpgleunoccvxuegntfrxauxsjquourcegksmefbikygeemratkxthxlspihjzlqiviucpxkzwqfaeyaxlassxgkoqtypeoxcyuenropphwsnvuniufatnmidiivoyuqyounilcxuzphwkuegcgcpumveuqtumlsbgfltiipjawhmtlsvtumngjucbnwoyorgucbekqpmpwykjrhcntrwflygyxwznnhjpjnczeoqanajghltdfpiqqqrpnscazmsibukgkagxdsfkfzpkqzdiquxdmafnmzwpvgvgieflwlybmpocprlflikzkrmyrkomsjsbnaollonzebbtsoyfzkgjbuisfwhxhbaxbpiofxnjareufebeohqqeykjwrif");
    String T = "tjcwallfkarlrvfxchdqqtiutvfpoovjxzgxmtextvintpmvypnplyletrwhftreszdhshenfocadoxegkvrigxbzvleqckjdnsvvwkckncpdztjloauxaxwvibmmlxpbpmwnzaxmcopdiboydkvdisbqvpfiowjfjhsihrwlfnopodosnjxxdyqynvhbrqgcyamhrktzyhoomcgcoezrerssozvipekpezxyjqxjzlymqeqgkrzpjrjxqgfimszrtwrcoqmqbketqubbnbswsbwljdvwxupqtgtjwhzztdvjzwmnzglsjjftnapkwedpmybkfalyggjffyueegyopfhefyreeuvsswczznxfwimbghhlpgbelklticxoyugsrkrqzqxvyjyhqiufqvmdfzwpdvddqlvjvozwewuehslyahfsctwjsuyxsdaiqvtnlskpqewxyjxzrfttypftkdqcjtmzofnczxrrbpqzboastuntlsovyhxhalgqqtsrsmivbzxcnzwivkdhesccbcjbnsrelmvgygbbfyguyeetohavbfxehjfwbzconaulgwolwwhwblsruumyzmcivkfylhmyhjlphbadyjczwusrohrotvyqfdosncqwldmsfoyfyaeuuynifeyyaxqhcgaplsmywardorimtohnmuxsbysdxlkzrmrehfdffwitnqigepvslumoshrpserlsiqzpteupmneexkkmhdabrquyilqocegmuibpmxgbnkhkwszdxzeorapbmhpqlydhggyueevrqfdmxcrwdwmvwdwklmbykeismgmqnkjdpnqopjmtfyqeemopapnmvveierardkuuzmiqwwldwbhaowpqnfdjchrgarxfduzeihvedikakraapsqdxtmzdfidyfjebiiksfqxoazaucajusotmcuphcuikfmlqwxkcohsqhsmluyfmmaypupyzmgjtuwjrutvkdncmhpxbnenzeoqafrztxknuhwldsinxxpjegihtmwvsmnsseuneeaynzlqttdqqvzwbhdxvbupohjimdvskyqxxdosytioqjmusrrpsleiunsiroadosanxqfvknlcyxwqqpflcltxymfrciuscxfankvzzhcxgypxqwishdpfrxztftljqsjgcfhdmjcskrpapzswdkeujdqoydzryjxaoegcqiuccmcrnwosiunrzfhxkhoiqnlgurikzemdqqvgolyxfnqesydfhspxhadbtnntrzwkrtqgeoflcvrwvvdcptbwteanrnilpgvgalogbtsfrlcmifpxaswuzyjsltdazyjblxintcskpwwyenxeitahtjzfcdhebqfpqpcjtutltrjxhgbpccwvnxcsakwecdtuvvdqrybkskhbtlqvposclvwohusjalevijnbkrmcdvdwgvtxlmayhymotgztrfqbrddbwrfamkwvfqsseuqltfbolahfizgzelabehbtrzoyriqvfiianjozmxewwkvayxdceevvelvvwlrbtzbhlrgzomvmwqowpsbtwwqcknygyrjtsmcfdketwbhvrvripsdorvqbiqjgjsceeejjpqclrjglgatwsxklscmbvxjeplkvquehmgsdrzoyzkwthifbmtehtooibyerukygrtkkuaivkgotovvbrzkkpyurzaktljaeemoymztzfjswfpzyrjkgiezhfkcsvcwzomxblsatbupgkogdzqjjvlscvvwsxurogqpsirnjncsxsycrrmbozvsuqomoifcoxtifmvqzzkzrlblcpoqojikayyqgduuanfhbaacakbtvfezwurlezxgxwwfzqccfhlihlkjyodvtjvpemckoasnwotxgvbncnvlahxyvauqlrgrfkbtktjijeirzjgdydqzlnzgptxbmfsspwncrwmdcuudgdiegzdrodaeyzhngbfxxuzrtouviqzothvaiatxdvdewkrclyalsbclpyhaoiqkpnmaohxtdaxbmqexogkjjmwbmdbbntndhzfviconeopqgtpxbagkmclbioevkrarsfoajeonuddbytyzvejhixkqloovfoozweaflqtfvygttguomfyvcinqxdleuceqggxrysztrfomoibcnbzsjniisvpmxvcgbcxzuwsnegauqdwfgwxrlfcddrqcmuinwiotgigkqiggndlvblnmsppvghyucgjaqxtxfchxmqfctqxudkiwbxtjfrdjamlqjhnqjxnxpsbilzeqlomplgyszcdbopuawjshiigxcihpjngwbslnakhaondecxeahzjphpnjzwzikfhlzsacekeuxhzzfdboekflbuxbrwlbdsoenpgtzkowsnxzsypxkuilpfmgdyjisxnfmvzngnjacibugqrsmaebhqjlquvlsisggrumgfoiorfwjwpvyvzilkvefqpxbhfhbzvjfjuvcztlviiwdherszxzpowqvbxydukkdehhdaualdyomchgdgftublsflqkfculgbulbpmmsmdepmnstsnnwbhboynvchijkdunsiamvfsmtfgmywcuzjxnjkkmttxdwrqqcscxzwltcrhcnjwmvvbefmsafpddjvvlqzkpvvixoszebxqysufzgxznpesycnjsmvhhqshekiingboaxtafsbshikfkzqcllludddkpmufuxtuumwzearoidvseowrbhmfnprcdljxjcufqsfxsynceiwiwsayaofnfwrjjdiufcayxfxpfkujsqsrjurliwynuryduhaclackhzcmlwwfmqwvkngulgjfailwrlyenapubrimpgsbwqziehepqoxqyimrtnxinoerfmdutxyroxcuggjwwgmuqbnloltdzxuhkadfxbynaahhjtffbsasvpdjazoawjjilmnkqmyqohzxafdezfwuubfxatxzeghruvckjcdzjcxmjcqijoefrjcsfztlhadexfoijriswhgflyquouzggcaldtwntbrrihzrbjmxqsedtuxhpznvdpiiigxeiqvqywkziwyaocejxdrocgjhwtsezpijgfcgemhmifmyjqiiwbwahhidaakcrsnzapfedmqltemfxwntxktgtdkufuyxolwodesgsksopgtmcghbsahoetklpiievhsylnffxztmfmajvhmpkvdopnbevkirtdrgqwlnvfccrclomzewvhmmvnqszblsedywbkjxrlbkqpzeodajmovjboebrpvvuwrwgcvzqwjilimcnonaclldzpgshenbagzikuhatqigbwiokyqmjesktacaereezfojavkvoubprmdbkwqvakhvkjyrccxbhmkjjuexpimiwsliqbdqnknavlrxpqyxeiiofplwzaevurdkticiupipdbossorwyamdrabqchlwzqiuakcwblnembxgpofpqrvhtwxvekrfcbzenbaponvdqulhjqxbksjdogbutuzxwacjecysswxiqjqbbndlfmrvussnmtliupuyrusjqqshbkicxloezhbuztjlnuwjjshdmbodtgmuqwxpinvjuxdvqnifjgovyxalboquxcpnyofidaszxxuqkcxwjorhntuohkjemaunqzxtbpsznorzqoxbeqlsjfgrqrverprjqpcsgwtvkmxdauehlpzuvhnnzlrsclctcgnkdggpsfsuzxdmocjlmyyljsfmdjhhqurvczkwefsgcwusydemcezlyfzdqgkiacdcdqaivtqzpktpoxcsebfcifgznqhounsydtiamybdiusyuusyyacgfifubnivcdvfyfhmdipoejoyejwejzkqjqkkfvihyfoylvkqfbgjkxiqgguphfftxpdemnnktbkeyuwfxdoiayaghxxqeejbvnfiauuvvfbvbyazdojfplnwikmmowpjlwtnqolltmgwmgvpxgumshkctyphwethboxrcifxuluhurytathucrwwrlfosyxtnumrcrrzflibegugbbaeuxalihbagzvvtioybfzgshhzpbftegxafzmngpqoonlkxaizfhfaqnzhqeaxmzxrylhhbzvpmjzmjjlqsqqifrsywsvksscselzfkxuygwkrfvjyocivtatrvocnhghnrhsdgobomohsjqszucvofqhafjporwbkfvnllrbyfisqbmsvonltdttkekayhdimawampbmumempsemgrycxtxjximvppedqcfcrjonxuzmmeyuxywwruipeumvjqyzuednptcmpcybiyyfxueoqmfugsrpkqrhwwunqxzhbxjlxjsufwlwiqsqkkivcbcaxynpxoxalilirefwaqulipdtblqfmufmuubsiauvigfbmmaocubnjfgksyphswtmswgazdxhjflxrllufnjrupdmpvvhrovmneomhmvsrgpircezrnqlevavqvckmawzeknnyifrrwaiygjqwbdcxfynbcxpevoigdxgncyeyapzgyreaujxahmdlmsqhiaplywycwdbwuaestlunutpdgsoumgdujtfhrgjplmlfopzhauxwnmzvhqptwtqfovofdauoohvfyvmbplxeaxahnpclistwqhdtjzkyihdhcritdhydgamvpohahayxjesvdetomyvorfkuokzvknqzczblbaknecijjbxvbulszvpqholrcdtgdurgudtahbkqehlfczydwptefgvjkqpplivhlritsslxzxedyejutcyzjwivmamzijhwaprvhcuibaozfxwazubksmhfmvewfluhclelqyutzkvghrginivgucliareffbohottetnauzvcgmloztjfposzokfvpglyvgrbagbwfwzlkkcmfltwbmvpycmpjyngehhgnjfvheisqkgxyztznqrnsfcddsjkbtsqcxlldudtelsellgydxmycqbiknungoekmpvplblsmfwzrcywbmugeyqrnmevaihmacelfrfuwctpgtoifkqmigdxshussbhsgnflelfcnbsvugfcqvobdtaxcvsuwbmcosqsdxwsyoptwvxpsnsygsvippdmqrqeliyqgvkglazkvyzplrnrllcbafnryfxkoyawvlcgnesicrzoknhwyjxaqmtptrzhrbfmoiubonlqglgraqpyzmysashtlidcliqasmdgbpzicfbcvqcxecpeuyxhialtldvdqzkrqjbuqcwosanwceazcaikcufnxbrpadhsokmgnkuhkjeongnpiwblshyrirhkwqcmaqtnapvmzbamjqesmffgofxyzeivumixedkeshsrkvgulkozrvfgacezhpxdbhagknmewhwhccdnotmfotnqvsqehpkkwksgzfkyoifqmkdcfrivkvlhpdmwswjoibwkijiwrpmgbrigisbuomcfqqcimnzwovgzeqvyfkggbqomjpizemewcigqvfnmdvljmijtspcwgqkpiuomhuijdocywpmzmxiaiswajyypokgwfwkorsxujzsfqfgfeohmidpsbpjsaqqhmbvdtuxewsjozptfgbuqejiiasaaiqjwreanzztxtfluwfaciutofdwfvyifrrckyezzdtwzyhtuotxypsnuvlctmkydbcnbgnamiwngegaucztrtiopvqkhaikdnafvpfjxewdkdfeffzyyhmpdezkgkxkzzruoyencasmfxtkeslfazwfeyrkkowjpndfohsqkmfpmycsqnxsmwxsurdefbpxrscfcxxmjcdvcfovunnjlnbheenlickzvnezkbnvgqriyhfuwtdiifpolfbthmsezydlasgijzsjgyddthndmerxjutrglvhkbsmtbweaopjotohcbkqhdpraikguhqgikselokoeaxtvncxqpnxueounabjitpsdjypxjfdlcygpbdfssemwvhjvkufdjagsyxrjcxugvaxwfeqxldwgkhbwcjzljhghhztlwnusorqtyhtjkppwwsuydvmmcswlrpxqetiskaokhhvtymruhlvkghzlbvwumvkqoulbvzneijygvpccbxnjemuhjtcxompfjagebtijcirayucbrrkwdfdoyhnowdtfdzlfvtqzcizcccdttnrzuxpsgdjcgchjesqzohasrautezoxmiyjhqbntkqzpzktudukmucduyyyxoylwyufoythklizmvsiamsmhwrdurerfcdeczjrxeairlhgicoiojwxfndcehvllapnabgxhjfbunherxmfgkfoxiqdrlocmgrmjylqhrtdgcjxaxeihlygvojmczqqfieqgpjpicuqfdphcupjhopdeyriziyfqexyugneljvqokzqgqzogqtlokvpswukauiwaxblgydwicmrtnhzdamtkijfefpgzydcxepgetcoxrfsgmummgfzqqgwyvdzvgbtmrzsmdglumebaynpguoxnwvlrykngoiuhjmvmvgjjkmjkbyrvoqkklvemituwbrfmfayaghjtvvdwtuukkewycamkvgiwznifnjhtsrmomsmhmgtzlqznslzothmvuxahijcaarqojdyxjrwwjxleiksetgzndbwlkbonseddhbatvzrgwdkhpeljlmyonvczwqoqustevtzssqzrvlynqjwbxurmrvlplafiuyopupkohjfdwdsmooplkjxcelrsnzcjzqythkiovptstlejbwxmfwqlhjdwkhfkzgdgqnvmfqeackjdggrndjzmteldmqplytspipdnfhpjtzpchgpmqqblvmqjcgubboslfvbleqwzplbzstlkjmxgynpdvmgwaakgxltdbpcrdesvzdhisepstexpqdntfxzciufklkdoqvlpasxswmlqtihlouevufaczfonrrbhonyphwhgkuwfffrvbvqvbqacyoppitzwixnpvvvirtnwygrcckxfaxjaozswurucqitofrwyklvlsonwymdbkmbantyhmsldcfrjwpiiafvutisuxvvjiyjxshfebsjgxcpvrzguebqamjujersopbmhktonlztlcjivkoppqgpcrjlvafmriazstpfgobtsmcowqdlilckclxancmfmogehxmplnjeznuvdxjojlynzynmcztpnbtwyhomwzsvmmtrlefupgkoexdgzyvoarlyybmvaesoqnhcrpyhvixbichhbfvbwibwjotlumbnbjptypcvazwicdpdkmggvjdezwsukthmeyfenjzpivzmborlbzuyjzeivwcisluwjbdzmcouaojdeaqhakfljkthiypcjlztoesagiwiyhlfkcmmouxygqfqowtajutzynbexxpwhabrepdtlatrsakjwdjhxcgvrhbogsmntpezfjuqjnkenunlzwswyytrhwfqpsdkhkjotehfttjhpwuosnmsnfjxbqnskqcscmgyspdniouxmplgexatelqgbdymudgyrmwjebympvkdmxjqvjjzuahwdikzlqacewniylvpghtseckboudiwkgbsqdfidhnbwxziurqbmjiovjcronnzvrtmubwzilahjcpfthjfsrvocafrpvskmnyiovhzijgrlxxgeinxzlndnswcnoozenhskqrsaxmnwlubtjaswnxjfunvkcuopejbwatwafmwqizjrzdomdzuznplplcgplhmncknkwmaelmynrozunvtckfrwjznuibhlmxvklxrwlpjddlxsgtvtaopjoefwgojslgewevvpkguprukanftptkajofiuvrohytfeqctdjoscicexjzofjooavwwqqnzoifsyjnbsdwvlazkmbyrmofmlelcuybkiicgsrkmvijikjtwmtnykvlvbyxxwbheljmfcysxgbgrmbpyufxgckcdibagehmqsaxuzpuhtaahbqgxxfezriebjejqnvghvfdwvxeqzexcwfatmgufqygbeprtmxaidggafksfrrjpxjllrfrhgxlkpcebcglhoshuebbvsknrwydpfuzltkwimcmoixvxpetufurygepeeuukqtxkdturvgggebfviypnujncnfhubbeswbcbxysufyclchgkhyzdhpgwwpqxswpoarpxsjtxiwkmdsynqxszoyykpqihnxxnromjyfiidsujkswdroeipcsevaoialxtalcasmiwgefsybvprcxgesnbudjuoinqtrsmfailenecqbuhdtqklflgixcwfspzlhnrkxeqkivckplpkbzwgneokinpaeqonffxcjqoulqgacvkfximkocuzplbvcpjhwimgyacjrilvdyaagokxrgwiszeuppdlxptwsxhjftglrgnzvyyogwwywaunraappunhruszpykghxiexgsefoebrzoizozqkmaejufyvpfcopgmxascqwdjxtswktpwgpapjfpewuinlqbjqtlhrrqpmknygcvmrxddfsjbpsociplbkzicsatengdtlahexkhcetgvlwyvojwykgccjxjwgmrroyxlkfqqyymlukgivdjglliultqrhjkvhhjtozkoxyztiicfnllyywtlplcxvmuewdykckzbiavsizxvmlumjnyaaazvpualxaxcmwfuxaofzndnyxajmykmyjkpfmcfoahawmwvpmdqzgeafejtcthcfrumfwrtwyvvnrsedxuffyfkeofgknlrghdmdvhcvdzkwrocrjhwjmjocowmbjfkqjwwgfydixohqejjpqgzeppgdlvhxarnlpjtujzfipobtxkjpqivhzdlkyujbnxoeikasjfaytdrsochgmeolizygvhjieampkmhvujtpnmidrrjdwxpeynmvuydbyrqkgzfakyrzmbcdtohrnyvmljhhhnkhgarmbwomwilljqocextrvqficnslcctkdkxejnqhvesfuhjygfrcxxmzmelohgamsxqbnjjipbpjkbjrgavdxhfjmsztyafqzxuoifzxgoitprkxshdroidacevcgcsicowktvkcmsvbkhbeoeveoahqfesnhmvldsxwphabaxfmegsukpzaqiqxmkktyigbaeaplatnyybeetbqjcqbkgywsksqsyuueumswfhwdjbheczpgpqqxqmpbghvhdqlzcdwjlsmwvvazuqfjivgbkxcsgzzkrljtqhebcqxxlaxhsesaaybmxfeeheqyrbuhlylnpkkckjmcoefqdgccogivmytdcnnnoyadimhtytxoycedlihboqwdqrgshthvthacwjxuduidzoikwpnmflpwcwqlryinhkpdumzzihehvmttbggjrvfwtxjnvsadepiydizlrdvppoitvocokjkfgwscoshsjptmzuaryvgplokgdmxpvjuefpivjpqtgjhgdhxdefvqtfyysvpphmtmbbiksyfzjnhowesdlntestfmrucygvkjzuwrubqdozunfmqqblvvecivwktfhgoicmaxosceswrxkjqlulhznrwldsskyjvcvvbjneohchhntxankjozalkdddqdolfjihtxnjfrsogllhzvspwtagyflqizdqllzejmpqfhemvrvwonlkimsjrgzchdssqrybfnrkxxwsorochzopnhyhnajudbnvunxzbolyoisebijijxazeygtypqdsfbmsyltowtcgnhkxsshpugwvmptdhzvmahhauaoqwtzlajjsdsjyyftmxorepmtpvaprcgjaziobrxvxpexpmqdjgupwiknfkfwplfrmqfnjcigtmdkavnjcjbytlnayswpfegrumedqommdpwdlkpnvqnrdarbvqsauzuqnytdpfrsxovkakxvcmm";

    return minWindow( S.toString(),  T) ;
    }

    public String minWindow(String S, String T) {
        if(S==null||T==null||S.isEmpty()||T.isEmpty()) return "";
        int m = S.length();
        int n = T.length();
        if(n==1) return S.indexOf(T)>=0? T : "";
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> set = new HashMap<>();
//        HashMap<Character, Integer> setS = new HashMap<>();
        int countDistinct=0;
        for(char c: T.toCharArray()){
            if(set.containsKey(c)){
                set.put(c, set.get(c)+1);
            }else {
                set.put(c, 1);
                countDistinct++;
            }
        }
//        for(char c: S.toCharArray()){
//            if(setS.containsKey(c)){
//                setS.put(c, setS.get(c)+1);
//            }else {
//                setS.put(c, 1);
//            }
//        }
        for(Character i : set.keySet()){
          System.out.println(i+"- "+ set.get(i));
//            if(i=='z'){
//          System.out.println(i+": "+ setS.get(i));}
        }
        
        String res="";
        int min=Integer.MAX_VALUE;
        int countDSofar=0;
        int head=0;
        for(int i=0;i<m;i++){
            char ch = S.charAt(i);
            if(set.containsKey(ch)){
                if(map.containsKey(ch)){
                    int tmp = map.get(ch);
                    map.put(ch, tmp +1);
//                    if(ch=='z'){ System.out.println(map.get(ch) +"  "+set.get(ch)+" "+tmp);}
                }else{
                    map.put(ch,1);
                }
//                  if(ch=='z'){ System.out.println(map.get(ch) +"  "+set.get(ch));}
                if(map.get(ch).equals(set.get(ch)) ){ 
                    countDSofar++; 
//                    System.out.println(countDSofar+"  "+countDistinct);
                }
              
                if(countDSofar>=countDistinct){//every single char is found and including no of dup
                    char cur =S.charAt(head);
                    
                    while(!set.containsKey(cur) || map.get(cur)> set.get(cur)){
                        if(!set.containsKey(cur)){
                            head++;
                        } else {
                            if(map.get(cur)>set.get(cur)){
                                map.put(cur, map.get(cur)-1); 
                                head++;
                            }else break;
                        }
                        cur =S.charAt(head);
                    }
                    if(i-head+1 < min){
                        res = S.substring(head, i+1);
                        min = i -head +1;
                    }
                }
            }
        }
        return res;
    }
   
     public List<String> generateParenthesis(char[] A, int h ) {
         int n = A.length;
         if(h==n-1){ // end, need to output
             if( A[0]=='(' && A[n-1]==')' )    
                res.add(new String(A));
         }else {
            for(int i=h;i<n;i++){
                if( contains(A, h,i) || !permit(A,h,i)) continue;
                swap(A, h , i);
                generateParenthesis( A, h+1);
                swap(A, h , i);
            }
         }
        return res;
    }
    
    public void swap(char[] A, int i, int j){
        if(A==null||i==j||A[i]==A[j]) return;
        char tmp= A[i];
        A[i] = A[j];
        A[j] =tmp;
    }
    // if
    public boolean contains(char[] A, int h, int target){
        for(int i =h;i<target;i++){
            if(A[i]==A[target]) return true;
        }
        return false;
    }
    public boolean permit(char[] A, int h, int target){
        if (A[target]=='(') return  true;
        int count=0;
        int backcount=0;
        int i=0;
        for(i =0;i<h;i++){
            if(A[i]=='(') count++;
            if(A[i]==')') backcount++;
            if(count<backcount) return false;
        }
        if(i==h && count==backcount)  return false;
        return true;
    }
    
    public List<String> anagrams(String[] strs) {
        StringBuilder aaa = new StringBuilder();
        aaa.insert(0,"123").append("res");
        System.out.println(aaa.toString());
         List<String> res = new ArrayList<>();
        HashMap<String, String> map=new HashMap<>();
//         List<String> examined=new ArrayList<>();
        if(strs==null) return res;
        int n=strs.length;
        
//        if (n==1) {
//            if( !strs[0].isEmpty() ) res.add(strs[0]); 
//            return res;
//        }
//        map.put( getKey(strs[0]) , strs[0] );
        for(int i=0;i<n;i++){
          if( strs[i]==null || strs[i].isEmpty() ){ res.add(strs[i]); continue;}
            String key = getKey(strs[i]);
            if( map.containsKey(key) ){
                String first = map.put(key, strs[i]);
                if(!res.contains(first))
                    res.add(first);
                res.add(strs[i]);
            }else {
                map.put( key ,strs[i]);
            }                           
        }
        return res;
    }
    public int strStr(String a, String b){//violate match
        if(a==null||b==null||b.isEmpty()) return 0;
    int m=a.length();
    int n=b.length();
    char[] ach=a.toCharArray();
    char[] bch=b.toCharArray();
    int i=0;
    for(;i<=m-n;i++){
        if(ach[i]==bch[0]){
            for(int j=0;j<n;j++){
                if(ach[i+j]==bch[j]) {
                    if(j==n-1) return i;
                } else 
                    break;
            }
        }
    }
    return i==m-n+1 ? -1: 0;
    }
    public String getKey(String a){
        char[] ch= a.toCharArray();
        Arrays.sort(ch);
        return new String(ch);
       }
    
    
    public String addBinary(String a, String b) {
        if(a==null||a.isEmpty())return b;
        if(b==null||b.isEmpty())return a;
        StringBuffer res = new StringBuffer();
        int m =a.length();
        int n =b.length();
        char[] ach=a.toCharArray();
        char[] bch=b.toCharArray();
        int i=m-1;
        int j=n-1;
        int carryover=0;
        while(i>=0||j>=0||carryover>0){
            int achi = (i<0)? 0 : ach[i]-'0';
            int bchj = (j<0)? 0 : bch[j]-'0';
            int sum = achi +bchj+ carryover;
            carryover = sum>1? 1 : 0;
            res.insert(0,sum%2==0 ? '0':'1');
            i--;
            j--;
        }
        return res.toString();
        // return Long.toBinaryString(toDecimal(a)+toDecimal(b));
    }
    
    public List<String> findRepeatedDnaSequences(String s) {
        List<String>  res = new LinkedList<>();
        if(s==null||s.length()<20) return res;
        int n =s.length();
        for(int i=0;i<n-20;i++){
            String sub = s.substring(i,i+10);
            if(res.contains(sub)) continue;
            String remain = s.substring(i+10);
            if(remain.indexOf(sub)>=0)
            res.add(sub);
        }
        return res;
    }
    
  
    char[][] all = {{'a','b','c','!'},
                    {'d','e','f','!'},
                    {'g','h','i','!'},
                    {'j','k','l','!'},
                    {'m','n','o','!'},
                    {'p','q','r','s'},
                    {'t','u','v','!'},
                    {'w','x','y','z'}};
     public List<String> letterCombinations_2(String digits) {
        List<String> res = new LinkedList<>();
        if(digits ==null || digits.isEmpty()) return res;
        int n = digits.length();
        if(n==1){
            int last = digits.charAt(0) - '2';
            for(char sin: all[last]) 
            if(sin!='!') 
                res.add(sin+""); 
            return res;
        }
        for(int t=0;t<n;t++){
            char i= digits.charAt(t);
            if(i>='2'&&i<='9'){
                for( char j : all[i-'2']){
                    if(j=='!') continue;
                    //  List<String> list = letterCombinations(digits.substring(1));
                      if ( t==0||res.isEmpty()) { res.add(j+"");}
                       List<String> newl = new LinkedList<>();
                     for(String item : res){
                         item = item +""+  j;
                         newl.add(item);
                     }
                }
            }
        }
        return res;
    }
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if(digits ==null || digits.isEmpty()) 
            return res;
        int n = digits.length();
        if(n==1){
            int last = digits.charAt(0) - '2';
            for(char sin: all[last]) 
            if(sin!='!') 
                res.add(sin+""); 
            return res;
        }
        for(int t=0;t<n-1;t++){
            char i= digits.charAt(t);
            if(i>='2'&&i<='9'){
                for( char j : all[i-'2']){
                    if(j=='!') continue;
                     List<String> list = letterCombinations(digits.substring(t+1));
                    //  if (list.isEmpty())
                     for(String item : list){
                         String subs = j +""+ item ;
                         res.add(subs);
                     }
                }
            }
        }
        return res;
    }
    
    HashMap<String,Boolean > help=new HashMap<>();
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        List<String>  list = new LinkedList<>();
        if(s==null) return res;
        int n=s.length();
        if(n==1|| n==0) {
            list.add(s); 
            res.add(list); 
            return res;
        }
        char[] ch = s.toCharArray();
        for(int i=0;i<n;i++){
            if(i==0 ||(i==1&&ch[0]==ch[1]) ||(i>1&& isPalindrome(s.substring(0,i+1)))){
                List<List<String>> subStr = partition(s.substring(i+1));
                for(List<String>  item: subStr) {
                    item.add(0,s.substring(0,i+1));
                    res.add(item);
                }
            }
            
        }
        return res;
    }
    public boolean isPalindrome (String s){
        if(s==null)  return false;
        int n = s.length();
        if(n==1|| n==0)   return true;
        if(help.containsKey(s)) return help.get(s);
        int start=0,end = n-1;
        while(start<end){
            if(s.charAt(start)!=s.charAt(end)){
                help.put(s,false);
                return false;
            }
            start++;
            end--;
        }
        help.put(s,true);
        return true;
    }
    
    public int lengthOfLastWord(String s) {
        if(s==null||s.trim().isEmpty())  return 0;
        s=s.trim();
        return s.lastIndexOf(' ')==-1? s.length() : s.length()-s.lastIndexOf(' ')-1;
    }
    public int KMP ( String a , String b){
        if(a==null||a.isEmpty()||b==null||b.isEmpty()) return 0;
        int i=0;
        int j=0;
        int m = a.length();
        int n = b.length();
        int[] next = KMPnext_2(b);
        char[] ach = a.toCharArray();
        char[] bch = b.toCharArray();
        while(i<m&&j<n){
            if(j==-1 || ach[i]==bch[j]){
                if(j==n-1) return i-j;
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        return -1;
    }
            
    public int[] KMPnext_2(String a){
        if(a==null||a.isEmpty()) return null;
        int n = a.length();
        int[] res= new int[n];
        char[] ch=a.toCharArray();
        res[0]=-1;
        if(n==1) return res;
        
        for(int i=1;i<n;i++){
            int k=res[i-1];
            if(k<0){                    
                res[i]=0;
            }else{
                if(ch[k]==ch[i-1]){// match
                    res[i]=k+1;
                }else{
                    while( k>=0  && ch[k]!=ch[i-1]){
                        k = res[k];
                    }
                    if(k<0){                    
                        res[i]=0;
                    }else{res[i]=k+1;}
                }
            }
        }        
        return res;
    }  
    public int[] KMPnext(String a){
        if(a==null||a.isEmpty()) return null;
        int n = a.length();
        int[] res= new int[n];
        char[] ch=a.toCharArray();
        res[0]=-1;
//        if(n==1) return res;
        int i=0;
        int k=-1;
       while(i<n-1){
           if( k==-1||ch[i]==ch[k] ){
                i++;
                k++;
//                res[i]=k;
                if(ch[i]==ch[k]){ //  should continue find next 
                    res[i]=res[k];
                }else{
                    res[i]=k;
                }
           }else{
//               k=res[i-1];
               k= res[k];
           }
        }        
        return res;
    }
//    public boolean isAnagrams(String a, String b){
//        if(a.length()!=b.length()) return false;
////        if(map.put(a, a))
//        
//        if(a.equals(b))  return true;
//        
//        char[] ach=a.toCharArray();
//        char[] bch=b.toCharArray();
//        
//        Arrays.sort(ach);
//        Arrays.sort(bch);
//        return Arrays.equals(ach,bch);
//        
////        for(int i=0;i<b.length();i++){
////            for(int j=i;j<b.length();j++){
////                if(ach[j]==b.charAt(i)){
////                    // swap  ach[j] and ach[i], move forward
////                    char tmp = ach[j];
////                    ach[j]=ach[i];
////                    ach[i]=tmp;
////                    break;
////                }   
////                if(j==b.length())
////                return false;
////            }
////        }
////        return true;
//    }
}
