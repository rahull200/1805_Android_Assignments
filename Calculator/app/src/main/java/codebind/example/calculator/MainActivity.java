package codebind.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    Button one,two,three,four,five,six,seven,eight,nine,zero,mult,div,plus,min,clear,eq,dot;
    TextView ans;
    int dotcount=0;
    String exp="";
    char lastchar=' ';
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ans=(TextView)findViewById(R.id.ans);

        one=(Button)findViewById(R.id.one);
        two=(Button)findViewById(R.id.two);
        three=(Button)findViewById(R.id.three);
        four=(Button)findViewById(R.id.four);
        five=(Button)findViewById(R.id.five);
        six=(Button)findViewById(R.id.six);
        seven=(Button)findViewById(R.id.seven);
        eight=(Button)findViewById(R.id.eight);
        nine=(Button)findViewById(R.id.nine);
        zero=(Button)findViewById(R.id.zero);
        plus=(Button)findViewById(R.id.plus);
        min=(Button)findViewById(R.id.min);
        div=(Button)findViewById(R.id.div);
        mult=(Button)findViewById(R.id.mult);
        clear=(Button)findViewById(R.id.clear);
        eq=(Button)findViewById(R.id.eq);
        dot=(Button)findViewById(R.id.dot);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        mult.setOnClickListener(this);
        div.setOnClickListener(this);
        plus.setOnClickListener(this);
        min.setOnClickListener(this);
        eq.setOnClickListener(this);
        dot.setOnClickListener(this);

        clear.setOnClickListener(this);
        clear.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(ans.getText().toString().length()>8){
            ans.setTextSize(35);
        }
        if(v.getId()==R.id.one){
            appendValue('1');
        }
        if(v.getId()==R.id.two){
            appendValue('2');
        }
        if(v.getId()==R.id.three){
            appendValue('3');
        }
        if(v.getId()==R.id.four){
            appendValue('4');
        }
        if(v.getId()==R.id.five){
            appendValue('5');
        }
        if(v.getId()==R.id.six){
            appendValue('6');
        }
        if(v.getId()==R.id.seven){
            appendValue('7');
        }
        if(v.getId()==R.id.eight){
            appendValue('8');
        }
        if(v.getId()==R.id.nine){
            appendValue('9');
        }
        if(v.getId()==R.id.zero&&exp.equals("0")==false){
            appendValue('0');
        }
        if((v.getId()==R.id.plus)&&exp!=""&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*')){
            appendValue('+');
            if(dotcount==1){
                dotcount--;
            }
        }
        if((v.getId()==R.id.mult)&&exp!=""&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*')){
            appendValue('*');
            if(dotcount==1){
                dotcount--;
            }
        }
        if(v.getId()==R.id.min&&(lastchar!='+'&&lastchar!='-')){
            appendValue('-');
            if(dotcount==1){
                dotcount--;
            }
        }
        if((v.getId()==R.id.div)&&exp!=""&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*')){
            appendValue('/');
            if(dotcount==1){
                dotcount--;
            }
        }
        if((v.getId()==R.id.dot)&&dotcount!=1){
            appendValue('.');
            dotcount++;
        }
        if(v.getId()==R.id.clear){
            exp=backspace(exp);
        }

        if(v.getId()==R.id.eq&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*'&&lastchar!='.')&&exp!=""){
            appendValue('=');
            exp=String.valueOf(calculate(exp));
            dotcount++;
        }
    }

    // Append character to exp
    private void appendValue(char ch) {
        exp += ch;
        String tmp = String.valueOf(ch);
        ans.append(tmp);

        lastchar = ch;
    }

    // Clear last character from exp
    private String backspace(String exp) {
        String ch= "";
        String ex=exp;
        if (ex != null && ex.length() > 0 ) {
            if(ex.charAt(ex.length()-1)=='.'){
                dotcount=0;
            }

            ex = ex.substring(0, ex.length() - 1);
        }
        exp=ex;
        if(exp==""){
            lastchar = ' ';
        }
        ans.setText(ex);
        return exp;
    }

    // Calculate exp
    private double calculate(String exp){
        String finans ="";

        exp = divide(exp);
        exp = multiply(exp);
        exp = add(exp);
        exp = subtract(exp);

        finans = getFinalstring(exp);

        ans.setText(" "+finans);

        return Double.valueOf(finans);

    }

    // Get final exp
    private String getFinalstring(String exp) {
        String finans="";
        for(int i=0;i<exp.length();i++){
            if(exp.charAt(i)=='='){
                break;
            }
            finans += exp.charAt(i);
        }
        if(finans.charAt(0)=='+'){
            finans = finans.substring(1,finans.length()-1);
        }
        return finans;
    }

    private String divide(String exp) {
        int j,k;
        String minexp="";
        Double subans = 0.0;
        String tempnum1="",num1="",num2="";
        int validflag=1;
        char lastchar = ' ';
            while(validflag>0){

                for(int i=0;i<exp.length();i++){
                    validflag = 0;
                    boolean minpresent = false;
                    if(exp.charAt(i)=='/'&&i!=0){
                        validflag++;
                        //get num2
                        j=i+1;
                        num2 = getNum2(j,exp,i);

                        //get num1
                        j=i-1;
                        while(j>=0&&exp.charAt(j)!='+'&&exp.charAt(j)!='/'&&exp.charAt(j)!='*'&&exp.charAt(j)!='='){
                            if(exp.charAt(j)=='-'){
                                minpresent = true;
                                break;
                            }else{
                                tempnum1 += exp.charAt(j);
                                j--;
                            }

                        }
                        if(j>0){
                            lastchar =exp.charAt(j);
                        }
                        for(k=tempnum1.length()-1;k>=0;k--){
                            num1 += tempnum1.charAt(k);
                        }
                        //check if num1 is negative
                        if(minpresent){
                            num1 = "-"+num1;
                        }
                        // Calculate and update exp
                        minexp = num1 + "/" + num2;
                        subans = Double.valueOf(num1) / Double.valueOf(num2);
                        //check if sub answer after division is too large
                        if(subans.toString().length()>11){
                            DecimalFormat df = new DecimalFormat("#.#########");
                            df.setRoundingMode(RoundingMode.CEILING);
                            String s = df.format(subans);
                            subans =Double.valueOf(s);
                        }

                        exp=updateString(exp,minexp,subans,lastchar);
                        break;
                    }
                    num1="";
                    num2="";
                    tempnum1="";
                }
                if(validflag==0){
                    break;
                }

            }
        return exp;
    }

    private String multiply(String exp) {
        int j,k;
        String minexp="";
        Double subans = 0.0;
        String tempnum1="",num1="",num2="";
        int validflag=1;
        char lastchar = ' ';
        while(validflag>0){

            for(int i=0;i<exp.length();i++){
                validflag = 0;
                boolean minpresent = false;
                if(exp.charAt(i)=='*'&&i>0){
                    validflag++;
                    //get num2
                    j=i+1;
                    num2 = getNum2(j,exp,i);

                    //get num1
                    j=i-1;
                    while(j>=0&&exp.charAt(j)!='+'&&exp.charAt(j)!='/'&&exp.charAt(j)!='*'&&exp.charAt(j)!='='){
                        if(exp.charAt(j)=='-'){
                            minpresent = true;
                            break;
                        }else{
                            tempnum1 += exp.charAt(j);
                            j--;
                        }

                    }
                    if(j>0){
                        lastchar =exp.charAt(j);
                    }
                    for(k=tempnum1.length()-1;k>=0;k--){
                        num1 += tempnum1.charAt(k);
                    }
                    //check if num1 is negative
                    if(minpresent){
                        num1 = "-"+num1;
                    }
                    // Calculate and update exp
                    minexp = num1 + "*" + num2;
                    subans = Double.valueOf(num1) * Double.valueOf(num2);
                    exp=updateString(exp,minexp,subans,lastchar);
                    break;
                }

            }
            num1="";
            num2="";
            tempnum1="";
            if(validflag==0){
                break;
            }

        }
        return exp;
    }



    private String add(String exp) {
        int j,k;
        String minexp="";
        Double subans = 0.0;
        String tempnum1="",num1="",num2="";
        int validflag=1;
        char lastchar = ' ';
        while(validflag>0){

            for(int i=0;i<exp.length();i++){
                validflag = 0;
                boolean minpresent = false;
                if(exp.charAt(i)=='+'&&i!=0){
                    validflag++;
                    //get num2
                    j=i+1;
                    num2 = getNum2(j,exp,i);

                    //get num1
                    j=i-1;
                    while(j>=0&&exp.charAt(j)!='+'&&exp.charAt(j)!='/'&&exp.charAt(j)!='*'&&exp.charAt(j)!='='){
                        if(exp.charAt(j)=='-'){
                            minpresent = true;
                            break;
                        }else{
                            tempnum1 += exp.charAt(j);
                            j--;
                        }

                    }
                    if(j>0){
                        lastchar =exp.charAt(j);
                    }
                    for(k=tempnum1.length()-1;k>=0;k--){
                        num1 += tempnum1.charAt(k);
                    }

                    //check if num1 is negative
                    if(minpresent){
                        num1 = "-"+num1;
                    }
                    // Calculate and update exp
                    minexp = num1 + "+" + num2;
                    subans = Double.valueOf(num1) + Double.valueOf(num2);
                    exp=updateString(exp,minexp,subans,lastchar);
                    break;
                }
                num1="";
                num2="";
                tempnum1="";
            }
            if(validflag==0){
                break;
            }

        }
        return exp;
    }

    private String subtract(String exp) {
        int j,k;
        String minexp="";
        Double subans = 0.0;
        String tempnum1="",num1="",num2="";
        int validflag=1;
        char lastchar = ' ';
        while(validflag>0){

            for(int i=0;i<exp.length();i++){
                validflag = 0;
                boolean minpresent = false;
                if(exp.charAt(i)=='-'&&i!=0&&i-1>=0&&exp.charAt(i-1)!='+'&&exp.charAt(i-1)!='-'&&exp.charAt(i-1)!='/'&&exp.charAt(i-1)!='*'){
                    validflag++;
                    //get num2
                    j=i+1;
                    num2 = getNum2(j,exp,i);


                    //get num1
                    j=i-1;
                    while(j>=0&&exp.charAt(j)!='+'&&exp.charAt(j)!='/'&&exp.charAt(j)!='*'&&exp.charAt(j)!='='){
                        if(exp.charAt(j)=='-'){
                            minpresent = true;
                            break;
                        }else{
                            tempnum1 += exp.charAt(j);
                            j--;
                        }

                    }
                    if(j>0){
                        lastchar =exp.charAt(j);
                    }
                    for(k=tempnum1.length()-1;k>=0;k--){
                        num1 += tempnum1.charAt(k);
                    }
                    //check if num1 is negative
                    if(minpresent){
                        num1 = "-"+num1;
                    }
                    // Calculate and update exp
                    minexp = num1 + "-" + num2;
                    subans = Double.valueOf(num1) - Double.valueOf(num2);
                    exp=updateString(exp,minexp,subans,lastchar);
                    break;
                }
            }
            num1="";
            num2="";
            tempnum1="";
            if(validflag==0){
                break;
            }

        }
        return exp;
    }

    // Get 2nd operand in operation
    private String getNum2(int j, String exp,int i) {
        String num2 = "";

        while(exp.charAt(j)!='+'&&exp.charAt(j)!='-'&&exp.charAt(j)!='/'&&exp.charAt(j)!='*'&&exp.charAt(j)!='='){
            if(j>i+1&&exp.charAt(j)=='-'){
                break;
            }
            num2 += exp.charAt(j);
            j++;
        }
        return num2;
    }

    // Update exp
    private String updateString(String exp, String minexp,Double subans,char lastchar) {
        String sub = subans.toString();

        if(lastchar=='+'&&subans>=0){
            exp = exp.replace(minexp, sub);
        }else if(lastchar=='-'&&subans>=0){
            exp = exp.replace(minexp, "+"+sub);
        }else if(lastchar=='*'&&subans>=0){
            exp = exp.replace(minexp, sub);
        }else if(lastchar=='/'&&subans>=0){
            exp = exp.replace(minexp, sub);
        }
        else if(lastchar=='+'&&subans<0){
            exp = exp.replace("+"+minexp, sub);
        }else if(lastchar=='-'&&subans<0){
            String sub2 = sub.substring(1, sub.length()-1);
            exp = exp.replace(minexp, sub);
        }else if(lastchar=='*'&&subans<0){
            exp = exp.replace(minexp, sub);
        }else if(lastchar=='/'&&subans<0){
            exp = exp.replace(minexp, sub);
        }else if(lastchar==' '){
            exp = exp.replace(minexp, sub);
        }
        return exp;
    }

    // Clear everything on long click
    @Override
    public boolean onLongClick(View v) {
        if(v.getId()==R.id.clear){
            exp="";
            dotcount=0;
            ans.setText("");
            ans.setTextSize(62);
            lastchar =' ';
        }
        return false;
    }
}