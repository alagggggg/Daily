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


## FINAL V1.5.1 Widget Load Hotfix
- Loại bỏ RemoteViews setBackgroundResource động, nguyên nhân có thể làm launcher báo Sự cố khi tải tiện ích sau khi lưu lựa chọn.
- Giữ nền ô tĩnh từ XML; trạng thái Timing đang chạy được làm nổi bật bằng tiền tố ▶ và màu chữ đỏ cam.
- Chronometer ẩn được reset bằng format %s thay vì null để tăng tương thích launcher.
- Giữ nguyên command-based Native, actionId, expectedState, 20 ô và đồng bộ hai chiều.

### V1.5.2 Widget Readability
- Tên hoạt động hiển thị tối đa 2 dòng, không dùng dấu ba chấm.
- Emoji tăng từ 12sp lên 17sp và vùng hiển thị tăng lên 22dp.
- Khi tên chỉ có 1 dòng, nội dung vẫn được căn giữa theo chiều dọc.
- Tiền tố ▶ chuyển sang ngay bên trái đồng hồ Chronometer chạy từng giây.
- Tăng tương phản màu: thời gian đỏ nâu #8E3B2F, Emoji đỏ cam #E14B2F, tên đỏ đậm #7A2335.

### V1.5.3 Timing Active Contrast
- Đồng hồ Timing đang chạy hiển thị trong nhãn nền cam nhạt, viền đỏ cam và chữ đỏ đậm.
- Tiền tố ▶ vẫn nằm ngay bên trái thời gian chạy từng giây.
- Emoji và tên của ô đang chạy có màu nổi bật hơn; ô chưa chạy dùng màu dịu hơn.
- Chỉ dùng thay đổi màu chữ và View hiển thị/ẩn, không khôi phục đổi nền RemoteViews động từng gây lỗi tải widget.

#### V1.5.4 Active Cell Background and Bidirectional Mode Sync
- Ô Timing đang chạy dùng lớp nền cam đào và viền đỏ cam được khai báo sẵn trong XML.
- Chỉ bật/tắt lớp nền bằng setViewVisibility; không dùng setBackgroundResource động.
- Mỗi lần đổi Times/Timing trong ứng dụng đều đi qua UPDATE_ACTIVITY, cập nhật Native state và dựng lại tất cả widget.
- Khi chuyển Timing đang chạy sang Times, trạng thái active được xóa theo chính sách DISCARD_AND_STOP để đồng hồ và nền chạy không bị treo.
- Thao tác Times/Timing từ widget cập nhật Native state rồi phát STATE_CHANGED để WebView đang mở nạp lại ngay.
- Widget luôn dựng PendingIntent mới theo mode và revision hiện tại, tránh thao tác theo chế độ cũ sau khi chuyển đổi.

#### FINAL V1.5.5 Conflict Guard Complete
- Dùng chung một STATE_LOCK cho lệnh từ widget, lệnh từ ứng dụng và đồng bộ toàn bộ state, loại bỏ cửa sổ race giữa hai khóa khác nhau.
- syncState dùng compare-and-set nghiêm ngặt: chỉ nhận state có revision đúng bằng Native revision hiện tại; state cũ hoặc vượt revision bị từ chối và ứng dụng tự nạp lại Native state.
- Widget kiểm tra mode và timingState đã render trước khi START/STOP; thao tác từ PendingIntent cũ không thể áp dụng sai sau khi hoạt động đổi Times/Timing hoặc đổi trạng thái ở widget khác.
- Khi command bị MODE_MISMATCH, EXPECTED_STATE_MISMATCH hoặc SESSION_MISMATCH, widget và ứng dụng được refresh lại ngay theo nguồn Native chính thức.
- actionId, recentActions, chống nhấn đúp 800ms, startedEpoch và revision tiếp tục được giữ để chống phát lại, nhấn nhanh và dừng nhầm phiên Timing.
- Nền chạy vẫn dùng active overlay khai báo sẵn và setViewVisibility, không dùng setBackgroundResource động.

#### FINAL V1.5.6 Widget Event Picker Fix
- Sửa lỗi WebView gửi revision bằng Date.now làm compare-and-set luôn từ chối, khiến Native state không nhận danh sách sự kiện và màn hình cấu hình widget trống.
- WebView giữ nativeRevision logic, gửi đúng revision hiện tại và tăng sau khi sync được chấp nhận.
- Khi nạp Native state, ứng dụng đồng bộ lại nativeRevision trước mọi lần ghi tiếp theo.
- Màn hình chọn sự kiện xóa danh sách cũ trước khi dựng lại, tự nạp lại trong onResume và không tạo dòng trùng.
- Nếu Native state chưa có sự kiện, hiển thị hướng dẫn cùng nút mở Daily Time để khởi tạo đồng bộ, thay vì để màn hình trắng.
- Giữ nguyên khóa giao dịch chung, compare-and-set, chống mode cũ, timingState cũ, session mismatch và đồng bộ Times/Timing hai chiều.


##### FINAL V1.5.8 Launcher 3 Rows Fix
- Widget nội dung 6 hàng x 5 cột, tối đa 30 sự kiện.
- targetCellHeight=3 cho Android 12 trở lên.
- minHeight và minResizeHeight đặt 180dp theo công thức launcher cũ 70 x 3 - 30 để widget thực sự chiếm 3 hàng thay vì vẫn bị xếp vào 2 hàng.
- Giữ resizeMode=horizontal để không thay đổi hành vi resize dọc và giữ nguyên toàn bộ logic đồng bộ.
