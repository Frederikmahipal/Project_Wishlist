package gruppe8.project_wishlist.wishlists;

import org.springframework.web.multipart.MultipartFile;

public record WishCreationRequest(String title,
                                  String url,
                                  Double number,
                                  String note,
                                  MultipartFile image)
{ }
