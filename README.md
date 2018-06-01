# CookBook


### 添加依赖


    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

  

    dependencies {
	        implementation 'com.github.115MRLI:CookBook:1.0.0'
	}

###  使用方法


     <book.cook.view.widget.FoldingLayout
                android:id="@+id/traffic_item"
                android:layout_width="match_parent"
                android:layout_height="135dp"
                android:tag="service_item">
            <!-- 添加子菜单信息  -->
     </book.cook.view.widget.FoldingLayout>


      private FoldingLayout mTrafficFoldingLayout;


      mTrafficFoldingLayout = ((FoldingLayout) findViewById(R.id.traffic_item));

      mTrafficBarLayout.setOnClickListener(new View.OnClickListener() {

                  @Override
                  public void onClick(View v) {
                      handleAnimation(v, mTrafficFoldingLayout, mTrafficLayout, mBottomView);
                  }
       });