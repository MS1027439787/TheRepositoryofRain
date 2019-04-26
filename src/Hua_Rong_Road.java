import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Hua_Rong_Road extends JFrame implements MouseListener,KeyListener,ActionListener {
    private static final int ERROR_MESSAGE = 0;
    private static final int WARNING_MESSAGE = 0;
    int cnt=0;
    Person person[]=new Person[10];
    JButton left,right,above,below;
    JButton restart=new JButton("重新开始");
    JButton about=new JButton("游戏背景");
    JButton help=new JButton("游戏帮助");
    JButton mouse=new JButton("鼠标操作");
    JButton key=new JButton("键盘操作");
    JButton message=new JButton("当前步数："+cnt);
    JButton begin=new JButton("开始游戏");
    JButton star=new JButton();
    String name[]={"曹操","关羽","张飞","黄忠","马超","赵云","兵","兵","兵","兵"};
    public Hua_Rong_Road(){
        init();
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200,200,640,800);//设置窗体初始位置以及大小的一个函数
        setVisible(true);//窗口可见
        //person[9].requestFocus();//获取焦点需要卸载setVisible后面才行
        validate();//使用validate方法是容器再次布置其组件，确保布局有效
    }
    public void init(){
        setLayout(null);
        message.setBackground(Color.ORANGE);
        add(restart);
        restart.setBounds(120, 640, 100, 50);
        //restart.setBackground(Color.RED);
        restart.addActionListener(this);
        add(about);
        about.addActionListener(this);
        about.setBounds(250,640,100,50);
        add(mouse);
        mouse.setBounds(280,40,100,50);
        mouse.addActionListener(this);
        add(key);
        key.setBounds(400, 40, 100, 50);
        key.addActionListener(this);
        add(help);
        help.setBounds(380, 640, 100, 50);
        help.addActionListener(this);
        add(message);
        message.setBounds(110,40,160,50);
        ImageIcon starr=new ImageIcon("timg (5).gif");
        star.setIcon(starr);
        star.setBounds(108,208,400,400);
        add(star);
        add(begin);
        begin.addActionListener(this);
        begin.setBounds(250,140,100,50);
        setVisible(true);
        left=new JButton();
        right=new JButton();
        above=new JButton();
        below=new JButton();
        add(left);
        add(right);
        add(above);
        add(below);
        //边界类
        left.setBounds(98, 98, 10, 520);
        right.setBounds(508,98,10,520);
        above.setBounds(98, 98, 420, 10);
        below.setBounds(98, 608, 420, 10);
        validate();
    }
    //游戏布局
    public	void map1()
    {
        for(int k=0;k<name.length;k++)
        {
            person[k]=new Person(k,name[k]);
            add(person[k]);
        }
        person[0].setBounds(208,108,200,200);//曹操
        ImageIcon caocao=new ImageIcon("src/images/曹操1.png");
        person[0].setIcon(caocao);
        person[1].setBounds(208,308,200,100);//关羽
        ImageIcon guanyu=new ImageIcon("src/images/关羽1.png");
        person[1].setIcon(guanyu);
        person[2].setBounds(108,308,100,200);//张飞
        ImageIcon zhangfei=new ImageIcon("src/images/张飞1.png");
        person[2].setIcon(zhangfei);
        person[3].setBounds(408,308,100,200);//黄忠
        ImageIcon huangzhong=new ImageIcon("src/images/黄忠1.png");
        person[3].setIcon(huangzhong);
        person[4].setBounds(108,108,100,200);//马超
        ImageIcon machao=new ImageIcon("src/images/马超1.png");
        person[4].setIcon(machao);
        person[5].setBounds(408,108,100,200);//赵云
        ImageIcon zhaoyun=new ImageIcon("src/images/赵云1.png");
        person[5].setIcon(zhaoyun);
        person[6].setBounds(108,508,100,100);//
        ImageIcon bing1=new ImageIcon("src/images/卒1.png");
        person[6].setIcon(bing1);
        person[7].setBounds(408,508,100,100);
        ImageIcon bing2=new ImageIcon("src/images/卒1.png");
        person[7].setIcon(bing2);
        person[8].setBounds(208,408,100,100);
        ImageIcon bing3=new ImageIcon("src/images/卒1.png");
        person[8].setIcon(bing3);
        person[9].setBounds(308,408,100,100);
        ImageIcon bing4=new ImageIcon("src/images/卒1.png");
        person[9].setIcon(bing4);
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e){//键盘按下
        Person man=(Person)e.getSource();
        if(e.getKeyCode()==KeyEvent.VK_DOWN)//下键
            gok(man,below);
        if(e.getKeyCode()==KeyEvent.VK_UP)//上键
            gok(man,above);
        if(e.getKeyCode()==KeyEvent.VK_LEFT)//左键
            gok(man,left);
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)//右键
            gok(man,right);
    }
    //键盘模式下的移动
    public void gok(Person man,JButton direction){
        cnt++;
        message.setText("当前步数："+cnt);
        boolean move=true;//可以移动
        Rectangle manRect=man.getBounds();
        int x=man.getBounds().x;
        int y=man.getBounds().y;
        if(direction==below)
            y=y+100;
        else if(direction==above)
            y=y-100;
        else if(direction==left)
            x=x-100;
        else if(direction==right)
            x=x+100;
        manRect.setLocation(x,y);
        Rectangle directionRect=direction.getBounds();
        for(int k=0;k<10;k++){
            Rectangle personRect=person[k].getBounds();
            if((manRect.intersects(personRect))&&(man.number!=k)){
                //intersects为矩形类的一个方法，可以判断是否相交
                for(Person man2:person){//遍历数组
                    if(goc(man2,direction)==true){
                        return;
                    }
                }
                move=false;
            }
        }
        if(manRect.intersects(directionRect)){
            for(Person man2:person){
                if(goc(man2,direction)==true){
                    return;
                }
            }
            move=false;
        }
        if(move==true)
        {
            man.setLocation(x,y);
        }
        int cx,cy;//曹操的位置
        cx=person[0].getBounds().x;
        cy=person[0].getBounds().y;
        if(cx==208&&cy==408)
        {
            win();
            return ;

        }
    }
    public void win()
    {
        JOptionPane.showMessageDialog(this, "恭喜少侠，成功帮曹操脱险，日后必大富大贵！\n"
                + "操作"+cnt+"步.震惊天下！");
        JButton winn=new JButton();
        ImageIcon winner=new ImageIcon("timg (1).gif");
        winn.setIcon(winner);
        winn.setBounds(108,108,400,500);
        add(winn);
        setVisible(true);
        for(int k=0;k<name.length;k++)
            this.remove(person[k]);
    }

    //判断是否可以进行移动
    public boolean goc(Person man,JButton direction){
        boolean move=true;//可以移动
        Rectangle manRect=man.getBounds();
        int x=man.getBounds().x;
        int y=man.getBounds().y;
        if(direction==below)
            y=y+100;
        else if(direction==above)
            y=y-100;
        else if(direction==left)
            x=x-100;
        else if(direction==right)
            x=x+100;
        manRect.setLocation(x,y);
        Rectangle directionRect=direction.getBounds();
        for(int k=0;k<10;k++){
            Rectangle personRect=person[k].getBounds();
            if((manRect.intersects(personRect))&&(man.number!=k))
                move=false;
        }
        if(manRect.intersects(directionRect))
            move=false;

        if(move==true)
            man.setLocation(x,y);

        return move;
    }
    public void gom(Person man,JButton direction){
        cnt++;
        message.setText("当前步数："+cnt);
        boolean move=true;//可以移动
        Rectangle manRect=man.getBounds();
        int x=man.getBounds().x;
        int y=man.getBounds().y;
        if(direction==below)
            y=y+100;
        else if(direction==above)
            y=y-100;
        else if(direction==left)
            x=x-100;
        else if(direction==right)
            x=x+100;
        manRect.setLocation(x,y);
        Rectangle directionRect=direction.getBounds();
        for(int k=0;k<10;k++){
            Rectangle personRect=person[k].getBounds();
            if((manRect.intersects(personRect))&&(man.number!=k))
                move=false;
        }
        if(manRect.intersects(directionRect))
            move=false;
        if(move==true)
            man.setLocation(x,y);
        int cx,cy;//曹操的位置
        cx=person[0].getBounds().x;
        cy=person[0].getBounds().y;
        if(cx==208&&cy==408)//正确位置应该为408，这里为了快速结束游戏，设置较为简单
        {
            win();
            return ;
        }
    }

    @Override
//重新开始新的一局游戏
    public void actionPerformed(ActionEvent e) {
        JButton b=(JButton)e.getSource();
        if(b==restart)
        {
            dispose();
            new Hua_Rong_Road();
        }
        if(b==about)
        {
            JOptionPane.showMessageDialog(this, "华容道游戏取自著名的三国故事，曹操在赤壁大战中被\n"
                    + "刘备和孙权的“苦肉计”、“火烧连营”打败，被迫退逃到华容道，又遇上诸葛亮的伏兵，\n"
                    + "关羽为了报答曹操对他的恩情，明逼实让，终于帮助曹操逃出了华容道。\n"
                    + "曹操逃出华容道的最大障碍是关羽，关羽立马华容道，一夫当关，万夫莫开。\n"
                    + "关羽与曹操当然是解开这一游戏的关键。\n"
                    + "四个刘备军兵是最灵活的，也最容易对付，如何发挥他们的作用也要充分考虑周全。\n"
                    + "“华容道”有一个带二十个小方格的棋盘，代表华容道。\n"
                    + "棋盘下方有一个两方格边长的出口，是供曹操逃走的。"	+ "");
        }
        if(b==help)
        {
            JOptionPane.showMessageDialog(this, "胜利条件：曹操到达地图中下方位置！\n"
                    + "点击开始游戏后，先在上方选择游戏方式\n"
                    + "键盘操作：使用小键盘的上下左右方向键控制角色的移动\n"
                    + "精确操作：键盘操作模式下，先用鼠标点击某个角色，然后使用方向键进行移动。\n"
                    + "鼠标操作：玩家通过点击当前角色人物的不同位置进行相应移动。\n"
                    + "注意，不能往左下，右下，左上，右上进行移动。\n"
                    + "选择完成操作方式中途尽量不要更换", "开始之前必看", WARNING_MESSAGE);
        }
        if(b==key)
        {
            b.setBackground(Color.green);
            for(int k=0;k<name.length;k++)
            {
                person[k].addKeyListener(this);
            }
            person[9].requestFocus();//获取焦点
        }
        if(b==mouse)
        {
            b.setBackground(Color.yellow);
            for(int k=0;k<name.length;k++)
                person[k].addMouseListener(this);
        }
        if(b==begin)
        {
            b.setBackground(Color.yellow);
            this.remove(begin);
            this.remove(star);
            map1();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mousePressed(MouseEvent e) {
        Person man=(Person)e.getSource();
        int x=-1,y=-1;
        x=e.getX();
        y=e.getY();
        int w=man.getBounds().width;
        int h=man.getBounds().height;
        if(y>h/2&&x>w/3&&x<(w*2)/3)
        {
            gom(man,below);//下面
        }
        if(y<h/2&&x>w/3&&x<(w*2)/3)
        {
            gom(man,above);//上面
        }
        if(x<w/2&&y>h/3&&y<(h*2)/3)
        {
            gom(man,left);//左
        }
        if(x>w/2&&y>h/3&&y<(h*2)/3)
        {
            gom(man,right);//右
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}

 class Person extends JButton implements FocusListener{
    int number;
    Person(int number,String s){
        this.number=number;
        addFocusListener(this);
    }
    public void focusGained(FocusEvent e){
        //setBackground(Color.GREEN);
    }
    public void focusLost(FocusEvent e){
        //setBackground(Color.RED);
    }

}