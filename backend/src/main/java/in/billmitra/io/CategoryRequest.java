package in.billmitra.io;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {
    private String name;
    private String description;
    private String bgColor;
}
