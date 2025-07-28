# stock-noti-kafka
Đây là project cá nhân, mục tiêu chủ yếu là luyện tập cách sử dụng Kafka ^^

1. Mô tả project:
- User đăng ký/đăng nhập tài khoản
- Cấu hình mức giá mong muốn cho cổ phiếu
- Nhận thông báo qua email khi giá cổ phiếu đạt ngưỡng đã đặt

Kiến trúc hệ thống:
![8f7a8603-f490-4bcb-89b4-08c95839fd1a](https://github.com/user-attachments/assets/a954e209-d41a-4392-8ae9-4a03e4c45ec5)

2. Công nghệ sử dụng:
- Giao tiếp giữa các service: Kafka
- Database: PostgreSQL, MongoDB
- Auth: Spring Security + JWT
- Send mail: JavaMailSender (SMTP)
- Deploy local: Docker compose
- Ngôn ngữ: Java (Spring boot)

3. Chức năng của service:
   
   3.1 price-simulator-service
   - Mô phỏng thay đổi giá cổ phiếu ngẫu nhiên
   - Gửi Kafka message lên topic price_update
  
   3.2 alert-config-service
   - Cho phép người dùng cấu hình cảnh báo cổ phiếu (symbol + giá mong muốn)
   - Lắng nghe topic price_update
   - Nếu điều kiện đạt, gửi Kafka message lên stock-alerts
     
   3.3 notification-service
   - Lắng nghe topic stock-alerts
   - Gửi email cảnh báo nếu điều kiện khớp
   - Lưu log gửi email vào MongoDB
     
   3.4 user-service
   - Xử lý đăng ký / đăng nhập bằng JWT
   - Cung cấp API nội bộ để lấy email theo userId


