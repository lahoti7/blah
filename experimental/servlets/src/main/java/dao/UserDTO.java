/*
 * Copyright (c) 2015 Edlink Inc. All rights reserved.
 *
 * Author: lahotipritesh@gmail.com
 */

package dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    private final String username;
    private final String password;
}
