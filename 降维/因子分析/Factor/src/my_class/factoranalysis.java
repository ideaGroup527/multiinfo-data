package my_class;
import my_class.Parameters;
public class factoranalysis {
	public int n = Parameters.x1.length-1;
	public int m = Parameters.x1[0].length-1;
	public int p ;
	//public double Variance = 0;
	public double[][] x = new double[n][m]; //原始数据标准化
	public double[][] r = new double[m][m];  //相关系数矩阵
	public double[][] rv = new double[m][m];
	public double[][] a = new double[m][m];//相关矩阵特征向量
	public double[] ch1 = new double[m];//特征值
	public double[] Lh = new double[m];//比例值
	public double[] Lh1 = new double[m];//累计比例值
	public double[][] aa ;//因子载荷矩阵
	public double[] xx = new double[m];//主因子数为p时的变量公因子方差
	public double[][] xxx ;//正交因子得分
	public double[][] L,s,bb;//斜交因子等矩阵
	
//  原始数据标准化变换
	public void Datastandard(){
		//  得到原始数据存入x二维数组中
		for(int r=0;r<n;r++){
			for(int c=0;c<m;c++){
				x[r][c]=Parameters.x1[r+1][c+1];
			}		
		}		
	    for(int i=0;i<m;i++){
	    	double b=0;
	    	for(int j=0;j<n;j++){
	    		b = b + x[j][i];
	    	}
	    	b = b/n;
	    	double c = 0;
	    	for(int j=0;j<n;j++){
	    		c = c + Math.pow((x[j][i]-b),2);
	    	}
	    	c = Math.sqrt(c/n);
	    	for(int j=0;j<n;j++){
	    		x[j][i] = (x[j][i]-b)/c;
	    	}    	
	    }  
	}
//相关系数矩阵的计算
	public void Related_Coefficient_Matrix(){
		for(int i=0;i<m;i++){
			for(int j=0;j<=i;j++){
				if(i==j){
					r[i][j] = 1 ; 
					rv[i][j] = 1;
				}
				else{
					double b = 0;
					for(int k=0;k<n;k++){
						b = b + x[k][i]*x[k][j];
					}
					r[i][j] = b/n;
					r[j][i] = r[i][j];
					rv[i][j] = r[i][j];
					rv[j][i] = r[i][j];
				}
			}			
		}		
	}
//计算特征值和特征矩阵	
	public void Characteristic_Matrix(double Jacobian,double Variance){
		for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				if(i==j)
					a[i][j]=1;
				else
					a[i][j]=0;
			}
		}
		double v=0;
		for(int i=0;i<m-1;i++){
			for(int j=i+1;j<m;j++){
				v = v + 2 * Math.pow(r[i][j],2);
			}
		}
		double ea = Math.sqrt(v);
		double eb = ea * Jacobian / m ;
		double k = 0;
		boolean value;
		ea=ea/m;
		do{
			value=false;
			for(int L=0;L<m-1;L++){
				for(int Q=L+1;Q<m;Q++){
					if(ea<Math.abs(r[L][Q])){
						k = 1 ;
						double va = r[L][L];
						double vb = r[L][Q];
						double vc = r[Q][Q];
						double p = (va - vc) * 0.5;
						if (p == 0)  
							v = 1;
						else
							v = -Math.signum(p) * vb / Math.sqrt(Math.pow(vb,2)+Math.pow(p,2));
						double si = v / Math.sqrt(2 * (1 + Math.sqrt(1 -Math.pow(v,2)))) ;
						double c0 = Math.sqrt(1 - Math.pow(si,2));
						for(int i=0;i<m;i++){
							 v = r[i][L];
							 p = r[i][Q];
							 r[L][i] = v * c0 - p * si ;
							 r[Q][i] = v * si + p * c0 ;
							 r[i][L] = r[L][i];
					         r[i][Q] = r[Q][i] ;
					         v = a[i][L] ;
					         p = a[i][Q] ;
					         a[i][L] = v * c0 - p * si;
					         a[i][Q] = v * si + p * c0;					               
						}
						r[L][L] = Math.pow(c0,2) * va + Math.pow(si,2)* vc - 2 * vb * si * c0;
						r[Q][Q] = Math.pow(si,2) * va + Math.pow(c0,2) * vc + 2 * vb * si * c0;
				        r[L][Q] = (va - vc) * si * c0 + vb * (Math.pow(c0,2) - Math.pow(si,2));
				        r[Q][L] = r[L][Q];
					}
				}				
			}	
			if(k == 1){
				k = 0;
				value = true;
			}
			else{
				if(eb<ea){
					ea = ea/m;
					value = true;
				}
			}
		}while(value);
		for(int i=0;i<m;i++){
			ch1[i]=r[i][i];
		}
		for(int i=0;i<m-1;i++){
			double P = ch1[i] ;
			int Q = i;
			for(int j=i+1;j<m;j++){
				if(P<ch1[j]){
					P=ch1[j];
					Q=j;
				}						
			}
			if(i<Q){
				ch1[Q]=ch1[i];
				ch1[i]=P;
				for(int L=0;L<m;L++){
					double m1=a[L][i];
					a[L][i] = a[L][Q];
					a[L][Q] = m1;
				}
			}
		}
		
		/*for(int i=0;i<m;i++){
			for(int j=0;j<m;j++){
				xx[i][j]=a[i][j];
			}
		}*/
		double D=0;
		double b=0;
		for(int i=0;i<m;i++){
			D = D + ch1[i];
		}
		for(int i=0;i<m;i++){
			Lh[i]=ch1[i]/D;
			b=b+Lh[i];
			Lh1[i]=b;
		}		
	}
	//计算因子载荷矩阵aa，主因子个数为p,xx
	public void Component_Matrix(int count){
		p = count;
		aa = new double[m][p];
		Parameters.drawdata = new double[m][p];
		double[][]A=new double[m][p];
		for(int i=0;i<m;i++){
			for(int j=0;j<p;j++){
				A[i][j]=a[i][j];
			}
		}
		double b=0;
		for(int j=0;j<p;j++){
			b = Math.sqrt(ch1[j]);
		    for(int i=0;i<m;i++)
		       aa[i][j] = A[i][j]* b;
		}
		for(int i=0;i<m;i++){
			double c=0;
			for(int j=0;j<p;j++){
				 c = c + Math.pow(aa[i][j],2);
			}
			xx[i]=c;
			c=Math.sqrt(c);
			for(int j=0;j<p;j++){
				A[i][j]=aa[i][j]/c;
				aa[i][j]=A[i][j];
				Parameters.drawdata[i][j]=aa[i][j];
			}
		}		
	}
	//方差极大正交旋转矩阵 va,aa
	public void Varimax_orthogonal_rotation(){
		double vb = 1.0E+20,va;
		int kl=1;
		boolean value=false;
		do{
			double b=0,c=0;
			for(int j=0;j<p;j++){
				double d = 0;
				for(int i=0;i<m;i++){
					b = b + Math.pow(aa[i][j],4); 
					d = d + Math.pow(aa[i][j],2);
				}
				c = c + Math.pow(d,2);
			}
			va = (b - c / m) / m;
			double PI = 3.14159;
			if(Math.abs(va - vb) < Parameters.Variance){
				value=false;
			}
			else{
				vb = va;
				//第KL次旋转方差值VA =(vb)
				double d,g,u,v;
				for(int k=0;k<p-1;k++){
					for(int j=k+1;j<p;j++){
						b = 0;
						c = 0;
						d = 0 ;
						g = 0;
						for(int i=0;i<m;i++){
							u = Math.pow(aa[i][k],2)-Math.pow(aa[i][j],2) ;
							v = aa[i][k] * aa[i][j] ;
							g = g + u ;
							b = b + v ;
							c = c + Math.pow(u ,2) - 4 * Math.pow(v, 2) ;
							d = d + u * v;
						}
						b = 2 * b ;
						d = 4 * d ;
						c = m * c - Math.pow(g,2) + Math.pow(b, 2) ;
						b = m * d - 2 * b * g;
						if(Math.abs(c) < 0.0000000001)
							d = PI / 2;
						else
							d = Math.abs(Math.atan(b / c));
						if(c<0)
							d = PI - d;
						if(b<0)
							d = -d;
						d = d * 0.25 ;
						b = Math.sin(d);
						c = Math.cos(d);
						for(int i=0;i<m;i++){
							d = aa[i][k];
							g = aa[i][j];
							aa[i][k] = d * c + g * b;
							aa[i][j] = g * c - d * b;
						}
					}
				}
				kl = kl + 1;
				value = true;
			}
		}while(value);
		// 输出 因子载荷矩阵方差 (va)   
		//方差极大正交旋转矩阵 aa
	}
	//正交因子得分 xxx
	public void orthogonal_factor(){
		double l1,y1;
		xxx = new double[n][p];
		double[][] ab=new double[p][m],ac=new double[p][m];
		double [] f = new double[p];
		for(int k=0;k<m;k++){
			l1 = rv[k][k];
			for(int j=0;j<m;j++){
				rv[k][j]=rv[k][j]/l1;
			}			
			for(int i=0;i<m;i++){
				y1 = rv[i][k];
				for(int j=0;j<m;j++){
					 if (i==k && j==k) rv[i][j] = 1/l1;
			         if (i!=k && j==k) rv[i][j] = -rv[i][j]/l1;
			         if (i!=k && j!=k) rv[i][j] = rv[i][j]-y1*rv[k][j];
				}
			}
		}		
		for(int i=0;i<m;i++){
			for(int j=0;j<p;j++){
				ab[j][i] = aa[i][j];
			}
		}
		for(int i=0;i<p;i++){
			for(int j=0;j<m;j++){
				ac[i][j]=0;
				for(int k=0;k<m;k++){
					ac[i][j] = ac[i][j] + ab[i][k] * rv[k][j];
				}
			}
		}
		for(int j=0;j<n;j++){
			for(int i=0;i<p;i++){
				f[i] = 0;
				for(int k=0;k<m;k++){
					f[i] = f[i] + ac[i][k] * x[j][k];
				}
				xxx[j][i] = f[i];
			}
		}		
	}
	//斜交因子相关矩阵L，斜交因子结构矩阵s，斜交因子模型矩阵bb
	public void oblique_factor(){
		boolean value ;
		int kk = 2;
		L = new double[p][p];
		s = new double[m][p];
		bb = new double[m][p];
		double [][] H = new double[m][p];
		double[][] ab=new double[p][m],T=new double[p][p],Y=new double[p][p],s1=new double[p][p];
		do{
			value = false;
			for (int i = 0;i<m;i++){
				for (int j =0;j<p;j++){
					double va = a[i][j] ;
					double vb = Math.abs(Math.pow(va,kk));
					H[i][j] = Math.signum(va) * vb;
				}
			}
			for(int i=0;i<m;i++){
	        	for(int j=0;j<p;j++){
	        		ab[j][i] = a[i][j];
	        	}
	        }
			for(int i=0;i<p;i++){
				for (int j = 0;j< p;j++){
					T[i][j] = 0 ;
					Y[i][j] = 0;
			        for (int k =0;k< m;k++){
			        	T[i][j] = T[i][j] + ab[i][k] * a[k][j] ;
			        	Y[i][j] = Y[i][j]+ ab[i][k] * H[k][j];
			        }
			        s1[i][j] = T[i][j];
				}          
			}
	        JZQN(s1, p);
	        for( int i=0;i<p;i++){
	        	for (int j=0;j<p;j++){
	        		T[i][j] = s1[i][j] ;
	        		L[i][j] = 0;
			        for (int k = 0;k<p;k++){
			        	L[i][j] = L[i][j] + T[i][k] * Y[k][j];
			        }
	        	}		                
	        }
	        for(int i=0;i<p;i++){
	        	for (int j =0;j<p;j++){
	        		T[j][i] = L[i][j];
	        	}		                
	        }
	        for(int i=0;i<p;i++){
	        	double y1 = 0;
	        	for (int j =0;j<p;j++){
	        		y1 = y1 + Math.pow(T[i][j],2);
				}
				y1 = Math.sqrt(y1);
				for (int j =0;j<p;j++){
					T[i][j] = T[i][j] / y1;
				}
	        }
	        for(int i=0;i<p;i++){
	        	for(int j=0;j<p;j++){
	        		Y[j][i] = T[i][j];
	        	}
	        }   	        
	        JZQN(Y, p);
	        for (int i = 0;i<p;i++){
	        	double y1 = 0;
	    	    for (int j = 0;j<p;j++){
	    	        y1 = y1 + Math.pow(Y[i][j],2);
	    	    }
	    	    y1 = Math.sqrt(y1);
	    	    for (int j = 0;j<p;j++){
	    	        T[i][j] = Y[i][j] / y1;
	    	    }
	        }
	        for (int i = 0;i<p;i++){
	    	    for (int j = 0;j<p;j++){
	    	    	Y[j][i] = T[i][j];
	    	    }
	        }
	        for (int i = 0;i<m;i++){
	        	for (int j = 0;j<p;j++){
	        		s[i][j] = 0;
		            for(int k = 0;k<p;k++){
		                s[i][j] = s[i][j] + a[i][k] * Y[k][j];
		            }
	        	}
	        }
	        for(int i =0;i<p;i++){
	        	for (int j = 0;j<p;j++){
	        		L[i][j] = 0;
			        for(int k=1;k<p;k++){
			            L[i][j] = L[i][j] + T[i][k] * Y[k][j];
			        }
	        	}
	        }
	        JZQN(T, p);
	        for(int i =0;i<m;i++){
	            for (int j = 0;j<p;j++){
	                bb[i][j] = 0;
	                for (int k =0;k<p;k++){
	                    bb[i][j] = bb[i][j] + a[i][k] * T[k][j];
	                }
	            }
	        }
	        if (kk < 5){
	        	kk = kk + 1;
	        	value = true;
	        }
		}while(value);		 
	}
	//矩阵求逆
	public void JZQN(double[][] s1,int p){
		double l, y;
		for(int k=0;k<p;k++){
			l = s1[k][k];
			for(int j=0;j<p;j++){
				s1[k][j] = s1[k][j] / l;
			}
			for(int i=0;i<p;i++){
				 y = s1[i][k];
			     for(int j=0;j<p;j++){
			    	 if(i == k && j == k) s1[i][j] = 1 / l;
			    	 if(i != k && j == k) s1[i][j] = -s1[i][j] / l;
			    	 if(i != k && j != k) s1[i][j] = s1[i][j] - y * s1[k][j];
			     }
			 }		           
		}
	}
	//斜交因子得分xxx
	public void  oblique_factor_score(){
		double[][] ab=new double[p][m],ac=new double[p][m];
		double[] f = new double[p];
		for (int i =0;i<m;i++){
            for (int j = 0;j<p;j++){
                ab[j][i] = s[i][j];
            }
		}
        for (int i =0;i<p;i++){
        	for (int j = 0;j<m;j++){
                ac[i][j] = 0;
                for (int k = 0;k<m;k++){
                    ac[i][j] = ac[i][j] + ab[i][k] * rv[k][j];
                }
        	}
        }
        xxx = new double[n][p];
        for (int j = 0;j<n;j++){
        	for (int i =0;i<p;i++){
                f[i] = 0;
                for (int k =0;k<m;k++){
                	f[i] = f[i] + ac[i][k] * x[j][k];
                }
                xxx[j][i] = f[i];
        	}
        }
	}
}
