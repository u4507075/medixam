package exam;

/**
 * Created by bon on 27/01/18.
 */

public class Content {
    public void addQuestion(Context context, String number, ArrayList<Question> question)
    {
        LinearLayout qpanel = new LinearLayout(context);
        int width = LayoutParams.MATCH_PARENT;
        int height = LayoutParams.WRAP_CONTENT;
        qpanel.setLayoutParams(new LayoutParams(width, height));
        qpanel.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l = new LinearLayout(context);
        l.setOrientation(LinearLayout.VERTICAL);
			/*
			GradientDrawable shape =  new GradientDrawable();
			 shape.setCornerRadius( 20 );
			 shape.setColor(0x33000000);
			 l.setBackground(shape);*/
        l.setBackgroundColor(P.QUESTIONBGCOLOR);
        l.setPadding(P.PADDING,0,P.PADDING,P.PADDING);

        l.setLayoutParams(new LayoutParams(width, height));

        TextView tnum = new TextView(context);
        tnum.setLayoutParams(new LayoutParams(width,height));
        tnum.setText(number);
        tnum.setTextSize(P.TEXTSIZENORMAL);
        tnum.setTextColor(P.TEXTFONTCOLOR);
        tnum.setBackgroundColor(P.TOOLPANELCOLOR);
        tnum.setGravity(Gravity.CENTER);
        qpanel.addView(tnum);
        qpanel.addView(l);

        if(question!=null)
        {
            for(int i=0;i<question.size();i++)
            {
                if(question.get(i).getType().equals("text"))
                {
                    TextView t = new TextView(context);
                    //Justifiedtext t = new Justifiedtext(context);

                    t.setLayoutParams(new LayoutParams(width, height));
                    //t.setText(question.get(i).getValue());
                    t.setTextSize(P.TEXTSIZENORMAL);
                    t.setTextColor(P.TEXTFONTCOLOR);
                    t.setText(setTextlayout(context, question.get(i).getValue(), question.get(i).getStyle()));

                    l.addView(t);
                }
                else if(question.get(i).getType().equals("image"))
                {
                    //ImageView image = new ImageView(context);
                    //image.setImageResource(R.drawable.loading);
                    //images.add(new Imagecollection(question.get(i).getValue(), image));
                    //l.addView(image);
                    //LinearLayout imagelayout = new LinearLayout(context);
                    //l.addView(imagelayout);

                    //images.add(question.get(i).getValue());

                    addImage(l, context, question.get(i).getValue());

					 /*
					try {
						ImageView image = new ImageView(context);
						byte[] bytes = android.util.Base64.decode(question.get(i).getValue().getBytes("UTF-8"),android.util.Base64.DEFAULT);
						//image.setScaleType(ScaleType.CENTER);
						BitmapFactory.Options options=new BitmapFactory.Options();// Create object of bitmapfactory's option method for further option use
		                options.inPurgeable = true; // inPurgeable is used to free up memory while required
		                Bitmap bm = BitmapFactory.decodeByteArray(bytes,0, bytes.length,options);//Decode image, "thumbnail" is the object of image file
		                //bm = BitmapFactory.decodeResource(getResources(), R.drawable.big);
		                //bm = resizeImage(bm);
		                image.setImageBitmap(bm);
		                image.setAdjustViewBounds(true);
		                //image.setScaleType(ScaleType.FIT_START);
		                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                        //lp.setMargins(100,0,100,0);
		                image.setLayoutParams(lp);
		                image.setPadding(0,P.PADDING,0,P.PADDING);
		                //image.setPadding(10,0,10,0);

						l.addView(image);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
					 /*
					 TextView t = new TextView(context);
					 t.setText(question.get(i).getValue());
					 t.setBackgroundColor(Color.RED);
					 t.setEnabled(false);
					 t.setMaxHeight(0);
					 LinearLayout ll = new LinearLayout(context);
					 String qheight = question.get(i).getHeight();
					 if(!qheight.equals(""))
					 {
						 ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,Integer.parseInt(qheight)));
					 }
					 ll.setOrientation(LinearLayout.VERTICAL);
					 ll.addView(t);
					 l.addView(ll);
					 */
                }
            }
        }

        this.question = l;
        this.addView(qpanel);

    }
}
