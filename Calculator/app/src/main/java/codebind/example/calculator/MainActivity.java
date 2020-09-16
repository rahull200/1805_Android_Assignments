package codebind.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        if(v.getId()==R.id.zero){
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
        if(v.getId()==R.id.min&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*')){
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

        if(v.getId()==R.id.eq&&(lastchar!='+'&&lastchar!='-'&&lastchar!='/'&&lastchar!='*'&&lastchar!='.')){
            appendValue('=');
            exp=String.valueOf(calculate(exp));
            dotcount++;
        }
    }


    private void appendValue(char ch) {
        exp += ch;
        String tmp = String.valueOf(ch);
        ans.append(tmp);

        lastchar = ch;
    }

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
        ans.setText(ex);
        return exp;
    }


    private double calculate(String exp){
        double num1=0;
        double num=0;
        String tempnum1="";
        char ch=' ',lastop=' ';
      //  Toast.makeText(getApplicationContext(),""+exp.length(),Toast.LENGTH_SHORT).show();
        for(int i=0;i<exp.length();i++){
            ch=exp.charAt(i);

            if(ch!='*'&&ch!='+'&&ch!='-'&&ch!='/'&&ch!='='){
                tempnum1 += ch;
            }
            else if((ch=='+')||(ch=='-')||(ch=='*')||(ch=='/')){

                if(tempnum1==""&&ch=='-'){
                    tempnum1 += "-";

                }else{

                    num1= Double.parseDouble(tempnum1);

                    if(lastop!=' '){
                        num = miniCalculate(num,lastop,num1);
                        //   Toast.makeText(getApplicationContext(),"hii"+num,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(num==0){
                            num = num1;
                        }else{
                            num = miniCalculate(num,ch,num1);
                        }
                    }
                    lastop=ch;
                    tempnum1="";

                }
            }
            else if(ch=='='){
                num1= Double.parseDouble(tempnum1);
                num = miniCalculate(num,lastop,num1);

                break;
            }
        }
        dotcount = 0;
        if((String.valueOf(num)).length()>11){
            ans.setTextSize(35);
        }
        ans.setText(""+num);
        
        return num;

    }

    private double miniCalculate(double num, char ch, double num1) {
            if(ch=='+'){
                num += num1;
            }
            if(ch=='-'){
                num -= num1;
            }
            if(ch=='/'){
                num /= num1;
            }
            if(ch=='*'){
                num *= num1;
            }

        return num;
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId()==R.id.clear){
            exp="";
            dotcount=0;
            ans.setText("");
            ans.setTextSize(62);
        }
        return false;
    }
}