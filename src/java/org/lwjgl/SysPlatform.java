/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lwjgl;

/**
 *
 * @author l3eta
 * @date Aug 5, 2016
 * @time 8:47:45 AM
 */
public class SysPlatform {

    public enum ArchType {
        ARM {
            @Override
            protected boolean matches(String name) {
                return "arm".startsWith(name);
            }
        },
        X64 {
            @Override
            protected boolean matches(String name) {
                return "amd64".equals(name) || "x86_64".equals(name);
            }
        },
        X86 {
            @Override
            protected boolean matches(String name) {
                //Due to how we match for ArchType we will have X86 be anything that makes it passed
                //the above ArchTypes
                return true;
            }
        };

        private final String name;

        private ArchType() {
            this.name = name().toLowerCase();
        }

        public String getName() {
            return name;
        }

        protected abstract boolean matches(String name);

        public static ArchType get() {
            final String name = System.getProperty("os.arch");
            for (ArchType type : values()) {
                if (type.matches(name)) {
                    return type;
                }
            }
            throw new LinkageError("Unknown ArchType: " + name);
        }
    }

    public enum PlatformType {
        LINUX {
            @Override
            public boolean matches(String name) {
                return name.startsWith("Linux")
                        || name.startsWith("FreeBSD")
                        || name.startsWith("OpenBSD")
                        || name.startsWith("SunOS")
                        || name.startsWith("Unix");
            }
        }, MACOSX {
            @Override
            public boolean matches(String name) {
                return name.startsWith("Mac OS X") || name.startsWith("Darwin");

            }
        }, WINDOWS {
            @Override
            public boolean matches(String name) {
                return name.startsWith("Windows");
            }
        };

        private final String name;

        private PlatformType() {
            this.name = name().toLowerCase();
        }

        public String getName() {
            return name;
        }

        protected abstract boolean matches(String name);

        public static PlatformType get() {
            final String name = LWJGLUtil.getPrivilegedProperty("os.name");
            for (PlatformType type : values()) {
                if (type.matches(name)) {
                    return type;
                }
            }
            throw new LinkageError("Unknown PlatformType: " + name);
        }
    }

    private final PlatformType type;
    private final ArchType arch;

    public SysPlatform(PlatformType type, ArchType arch) {
        this.type = type;
        if (type == PlatformType.MACOSX) {
            arch = ArchType.X86;//Fake the arch so the loadLibrary can act as normal without checking if it's mac.
        }
        this.arch = arch;
    }

    public PlatformType getType() {
        return type;
    }

    public ArchType getArch() {
        return arch;
    }

    public String getName() {
        return type.getName() + "-" + arch.getName();
    }

    public boolean isLinux() {
        return type == PlatformType.LINUX;
    }

    public boolean isMacOSX() {
        return type == PlatformType.MACOSX;
    }

    public boolean isWindows() {
        return type == PlatformType.WINDOWS;
    }
}
