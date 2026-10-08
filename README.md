# Daily Time Android 1.3.0

- Widget cố định 4 hàng × 5 cột, 20 ô.
- Times và Timing chạy trực tiếp bằng BroadcastReceiver, không mở ứng dụng.
- Timing cập nhật thời lượng định kỳ 15 giây, MM:SS hoặc H:MM:SS.
- Dữ liệu thao tác từ widget được lưu vào trạng thái Native đầy đủ và nạp vào Dòng thời gian khi mở ứng dụng.
- Xuất/nhập Excel .xlsx gồm Activities, Logs, Active, Settings.
- Sửa xóa toàn bộ lịch sử bằng lưu đồng thời localStorage và Native state.


## V1.3.2
- Sửa lỗi build WidgetConfigActivity bằng state() và types().
- Widget chiếm 5 cột × 2 hàng Android, rộng tối thiểu 300dp, cao 110dp.
- Bên trong luôn có 4 hàng × 5 cột, mỗi hàng khoảng 26dp.
- Thu gọn font, đệm và khoảng cách để 20 ô tải ổn định trong 110dp.
- Thêm RemoteViews dự phòng nếu dữ liệu cấu hình không hợp lệ.


## V1.3.3
- Khi Daily Time trở lại foreground, WebView đọc lại Native state trước khi dựng Ghi nhận và Dòng thời gian.
- Sửa trường hợp thao tác widget khi ứng dụng đang ở nền nhưng giao diện cũ không tự cập nhật khi mở lại.
- Khi xóa widget, xóa cấu hình selected_appWidgetId tương ứng.
- Bảo vệ callback chọn file Excel khỏi lỗi null.


## V1.3.4
- Khóa read-modify-write Native state để chống mất bản ghi khi nhấn nhanh hoặc nhấn từ nhiều widget.
- Revision tăng đơn điệu cho từng thao tác widget.
- WebView không được ghi đè Native state mới hơn; nếu bị từ chối sẽ nạp lại dữ liệu Native.
- Nhiều widget tiếp tục dùng cấu hình selected_appWidgetId riêng nhưng dùng chung lịch sử và trạng thái Timing chính thức.


## V1.3.5
- Lưu thời điểm mới theo UTC ISO 8601 có hậu tố Z để Timing không lệch khi đổi múi giờ.
- Dữ liệu cũ không có múi giờ vẫn được đọc theo múi giờ hệ thống để tương thích.
- Nhận TIME_SET, TIMEZONE_CHANGED và DATE_CHANGED để dựng lại widget sau đổi giờ hoặc sang ngày mới.
- BOOT_COMPLETED dựng lại đồng hồ từ StartedAt tuyệt đối và lập lịch cập nhật mới sau khởi động.
- ID bản ghi widget dùng revision để tránh trùng khi nhấn trong cùng mili giây.


## V1.4.0
- Ô widget bố cục ngang: icon trái, tên và thời gian bên phải.
- Timing dùng Chronometer chạy từng giây; không còn cập nhật theo bước 15 giây.
- Ô Timing đang chạy dùng nền và viền riêng để nổi bật.
- Chuyển Times sang Timing hoặc Timing sang Times trong ứng dụng được đồng bộ ngay sang mọi widget.
- Nếu chuyển Timing đang chạy sang Times, active cũ được xóa để không treo đồng hồ.
- Thao tác Times và Timing từ mọi widget vẫn ghi vào Native state và nạp lại vào ứng dụng.


## FINAL V1.5.0
- NativeCommandProcessor là cổng ghi chính thức cho ADD_TIMES, START_TIMING, STOP_TIMING, UPDATE_ACTIVITY, CLEAR_HISTORY và IMPORT_STATE.
- Widget gửi expected mode, expected timing state và startedEpoch thay vì toggle mù.
- actionId được tạo riêng cho từng lần nhận thao tác; recentActions chống phát lại.
- START/STOP Timing có compare-and-set và chống nhấn đúp 800ms.
- Native revision là bộ đếm logic, tăng đúng một lần cho command đã áp dụng.
- Ứng dụng đổi Times/Timing và xóa lịch sử bằng command nguyên tử.
