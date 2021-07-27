## Project giữa kỳ - Nhập môn An toàn thông tin

### Ứng dụng trao đổi Email bảo mật 

#### 1. Thành viên :pencil:

- Nguyễn Minh Tuân - 20183652

- Phạm Trung Hiếu - 20183535

- Nguyễn Kim Tùng - 20183661

#### 2. Công nghệ sử dụng :star:

- Java JSP/Servlet

- Java Mail

#### 3. Các kiến thức về an toàn thông tin được sử dụng :computer:

- Bảo mật mật khẩu người dùng với thuật toán BCrypt

- Bảo mật nội dung thư bằng cách mã hóa sử dụng hệ AES kết hợp RSA

- Xác thực bằng chữ ký số, sử dụng hàm băm SHA256 với thuật toán RSA


#### 4. Cấu hình project :computer:

- Cài đặt ```Java 1.8```, ```Eclipse IDE for Enterprise Java Developers``` (hoặc IDE khác).

- Import project và cài đặt server ```Apache Tomcat``` (v9.0).

- Cấu hình Server, Project Facets: chọn server Apache Tomcat v9.0.

- Cấu hình Java Build Path: thêm JRE library, Server library, các file jar trong thư mục ```WebContent/WEB-INF/lib```. Đồng thời, thêm các file jar ```javax.servlet.jsp.jstl```, ```sqljdbc``` vào trong thư mục lib của server.

- Cấu hình Deployment Assembly:

![image](https://user-images.githubusercontent.com/61912505/127116079-dfc9620c-e216-4670-aeb0-2d7390db1070.png)


- Sử dụng SQL Server: chạy file ```EncryptEmail-db.sql``` (trong thư mục ```db```).

- Cấu hình lại thông số kết nối cơ sở dữ liệu trong file ```src/db/SQLServerConnUtils_SQLJDBC.java```.

#### 5. Chạy project :computer:

- Click chuột phải vào tên project và Chọn ```Run on Server```.

- Chọn Server Apache Tomcat v9.0

- Next > 

- Finish

![image](https://user-images.githubusercontent.com/61912505/127117794-2208c93c-9913-4f4f-ab97-4ce599f6e4d0.png)








